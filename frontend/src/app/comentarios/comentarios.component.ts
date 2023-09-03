import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService, PoTableAction, PoTableColumn } from '@po-ui/ng-components';
import { HttpService } from 'src/app/service/http-service.service';
import { PontoTuristico } from '../pontos-turisticos/ponto-turistico';
import { Comentario } from './comentario';

@Component({
  selector: 'app-comentarios',
  templateUrl: './comentarios.component.html',
  styleUrls: ['./comentarios.component.css']
})
export class ComentariosComponent implements OnInit {
  idPontoTuristico: string;
	formPontoTuristico: FormGroup;
	title: string;
  pontoTuristico: PontoTuristico;

  lsActions: Array<PoTableAction> = this.carregarActions();
	lsColumns: Array<PoTableColumn> = this.carregarColunas();
  existeComentarios: boolean = false;

  constructor(private formBuilder: FormBuilder,
		private poNotification: PoNotificationService,
		private route: ActivatedRoute,
		private router: Router,
		private http: HttpService) { 

      this.formPontoTuristico = this.formBuilder.group({
				nome: ['', Validators.compose([Validators.required])],
				pais: ['', Validators.compose([Validators.required])],
				cidade: ['', Validators.compose([Validators.required])],
				melhorEstacao: ['', Validators.compose([Validators.required])]
			})
    }

  async ngOnInit(): Promise<void> {
    this.idPontoTuristico = this.route.snapshot.paramMap.get('idPontoTuristico');
    if (this.idPontoTuristico !== null && this.idPontoTuristico !== ""){
      await this.buscaDadosPontoTuristico();
      this.title = "Informações de "+this.pontoTuristico.nome;
      console.log(this.pontoTuristico);
      if (this.pontoTuristico.comentarios && this.pontoTuristico.comentarios.length === 0) {
        this.existeComentarios = false;
      } else {
        this.existeComentarios = true;
      }
    }
  }

  buscaDadosPontoTuristico(): Promise<void> {
		return new Promise<void>((resolve, reject) => {
		  this.http.get('ponto-turistico/' + this.idPontoTuristico).subscribe({
        next: (resposta) => {
          this.formPontoTuristico.patchValue({
            nome: resposta.nome,
            pais: resposta.pais.nome,
            cidade: resposta.cidade,
            melhorEstacao: resposta.melhorEstacao
          })
          this.pontoTuristico = {
            id: resposta.id,
            pais: resposta.pais,
            cidade: resposta.cidade,
            nome: resposta.nome,
            melhorEstacao: resposta.melhorEstacao,
            comentarios: resposta.comentarios
          };
          resolve();
        },
        error: (erro) => {
          this.poNotification.error(erro);
          reject(erro);
        }
		  });
		});
	}

  navegarParaCadastro(codigoComentario: string = ""){
	  this.router.navigate([codigoComentario], { relativeTo: this.route })
	}

  deletarCadastro(id: string): void {
		this.http.delete('comentario/' + id).subscribe({
			next: (response)=>{
				this.poNotification.success('Registro excluido com sucesso!');
				this.buscaDadosPontoTuristico();
			},
			error: (error)=>{
				this.poNotification.error(error);
			}
		})
	}

  carregarActions(): Array<PoTableAction> {
		return [
			{
				label: 'Editar',
				icon: 'po-icon-edit',
				action: (row: Comentario)=>{this.navegarParaCadastro(row.id)}
			},
			{
				label: 'Excluir',
				icon: 'po-icon-delete',
				action: (row: Comentario)=>{this.deletarCadastro(row.id)}
			}
		]
	}

  	carregarColunas(): Array<PoTableColumn>{
		return [
			{
				property: 'nome',
				label: 'Autor'
			},
			{
				property: 'data',
				label: 'Data',
			},
			{
				property: 'comentario',
				label: 'Comentário'
			}
		]
	}


}
