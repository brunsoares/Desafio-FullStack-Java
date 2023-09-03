import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { PontoTuristico } from 'src/app/pontos-turisticos/ponto-turistico';
import { HttpService } from 'src/app/service/http-service.service';
import { Comentario } from '../comentario';


@Component({
  selector: 'app-cadastro-comentario',
  templateUrl: './cadastro-comentario.component.html',
  styleUrls: ['./cadastro-comentario.component.css']
})
export class CadastroComentarioComponent implements OnInit {
  idPontoTuristico: string;
  idComentario: string;
	formComentario: FormGroup;
	title: string;
  pontoTuristico: PontoTuristico;
  comentarioSalvar: Comentario;

  constructor(private formBuilder: FormBuilder,
		private poNotification: PoNotificationService,
		private route: ActivatedRoute,
		private router: Router,
		private http: HttpService) { 

      this.formComentario = this.formBuilder.group({
				nome: ['', Validators.compose([Validators.required])],
				data: ['', Validators.compose([Validators.required])],
				comentario: ['', Validators.compose([Validators.required])]
			})
    }

    async ngOnInit(): Promise<void> {
      this.idPontoTuristico = this.route.snapshot.paramMap.get('idPontoTuristico');
      this.idComentario = this.route.snapshot.paramMap.get('idComentario');
      if (this.idComentario !== null && this.idComentario !== "" && this.idComentario !== "0"){
        await this.buscarDadosComentario();
        console.log(this.comentarioSalvar);
      }
      await this.buscaDadosPontoTuristico();
      console.log(this.pontoTuristico);
    }

  buscaDadosPontoTuristico(): Promise<void> {
		return new Promise<void>((resolve, reject) => {
		  this.http.get('ponto-turistico/' + this.idPontoTuristico).subscribe({
        next: (resposta) => {
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

  buscarDadosComentario(): Promise<void> {
		return new Promise<void>((resolve, reject) => {
		  this.http.get('comentario/' + this.idComentario).subscribe({
        next: (resposta) => {
          this.formComentario.patchValue({
            nome: resposta.nome,
            data: this.converterDataParaFormatoISO(resposta.data),
            comentario: resposta.comentario
          })

          this.comentarioSalvar = {
            id: resposta.id,
            nome: resposta.nome,
            data: resposta.data,
            comentario: resposta.comentario,
            pontoTuristico: resposta.pontoTuristico
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

  enviarPost(){
		this.http.post('comentario',this.comentarioSalvar).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro criado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}
	
	enviarPut(){
		this.http.put('comentario/' + this.idComentario,this.comentarioSalvar).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro atualizado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

  validarRegistro(): boolean{
		return this.formComentario.valid;
	}

  salvar() {
		if (this.validarRegistro()) {
			try {
        this.comentarioSalvar = {
          id: "",
          nome: this.formComentario.value.nome,
          data: this.converterDataParaFormatoBrasileiro(this.formComentario.value.data),
          comentario: this.formComentario.value.comentario,
          pontoTuristico: this.pontoTuristico
        };
        console.log(this.comentarioSalvar);
        if (this.idComentario === null || this.idComentario === "" || this.idComentario === "0") {
          this.enviarPost()
        } else {
          this.enviarPut()
        }
			} catch (erro) {
			  this.poNotification.error(erro);
			}
		} else {
			this.poNotification.error("Preencha todos os campos antes de salvar as alterações!")
		}
	}  		  

	voltar(){
    this.router.navigate(['/ponto-turistico/cadastro', this.idPontoTuristico, 'comentario']);	
  }

  converterDataParaFormatoBrasileiro(data: string): string {
    const dataObj = new Date(data);
    const dia = dataObj.getUTCDate().toString().padStart(2, '0');
    const mes = (dataObj.getUTCMonth() + 1).toString().padStart(2, '0');
    const ano = dataObj.getUTCFullYear().toString();

    return `${dia}/${mes}/${ano}`;
  }

  converterDataParaFormatoISO(dataBrasileira: string): string {
    const partes = dataBrasileira.split('/');
    if (partes.length !== 3) { throw new Error('Formato de data inválido. Use dd/MM/yyyy.'); }
  
    const dia = partes[0];
    const mes = partes[1];
    const ano = partes[2];
  
    const mesFormatado = mes.padStart(2, '0');
    const diaFormatado = dia.padStart(2, '0');

    return `${ano}-${mesFormatado}-${diaFormatado}`;
  }

}
