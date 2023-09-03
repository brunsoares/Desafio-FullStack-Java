import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService, PoTableAction, PoTableColumn } from '@po-ui/ng-components';
import { HttpService } from '../service/http-service.service';

@Component({
  selector: 'app-pontos-turisticos',
  templateUrl: './pontos-turisticos.component.html',
  styleUrls: ['./pontos-turisticos.component.css']
})
export class PontosTuristicosComponent implements OnInit {
  	lsActions: Array<PoTableAction> = this.carregarActions();
	lsColumns: Array<PoTableColumn> = this.carregarColunas();
	lsPontoTuristico: Array<PontoTuristicoLista> = [];

  constructor(
		private router: Router,
		private activatedRoute: ActivatedRoute,
    	private httpService: HttpService,
		private poNotification: PoNotificationService
  ) { }

	ngOnInit(): void {
		this.carregarPontoTuristico();
	}

  	navegarParaCadastro(codigoPontoTuristico: string = ""){
	  this.router.navigate(['cadastro', codigoPontoTuristico], { relativeTo: this.activatedRoute })
	}
	
	navegarParaDetalhes(codigoPontoTuristico: string = ""){
	  this.router.navigate(['cadastro', codigoPontoTuristico, 'comentario'], { relativeTo: this.activatedRoute })
	}

  	carregarActions(): Array<PoTableAction> {
		return [
			{
				label: 'Editar',
				icon: 'po-icon-edit',
				action: (row: PontoTuristicoLista)=>{this.navegarParaCadastro(row.id)}
			},
			{
				label: 'Excluir',
				icon: 'po-icon-delete',
				action: (row: PontoTuristicoLista)=>{this.deletarCadastro(row.id)}
			},
			{
				label: 'Detalhes',
				icon: 'po-icon-clipboard',
				action: (row: PontoTuristicoLista)=>{this.navegarParaDetalhes(row.id)}
			}
		]
	}

  	carregarColunas(): Array<PoTableColumn>{
		return [
			{
				property: 'nome',
				label: 'Nome'
			},
			{
				property: 'pais',
				label: 'País',
			},
			{
				property: 'cidade',
				label: 'Cidade'
			},
			{
				property: 'melhorEstacao',
				label: 'Melhor estação para visitar'
			}
		]
	}

	carregarPontoTuristico(){
		return this.httpService.get('ponto-turistico').subscribe({
			next: (resposta)=>{
				let registros: Array<PontoTuristicoLista> = []
				resposta.forEach(item => {
					let novoPontoTuristico: PontoTuristicoLista = {
						id: item.id,
						pais: item.pais.nome,
						cidade: item.cidade,
						nome: item.nome,
						melhorEstacao: item.melhorEstacao,
					}
					registros.push(novoPontoTuristico);
				});
				
				this.lsPontoTuristico = [...registros]
				console.log(this.lsPontoTuristico);
			}
		})
	}

	deletarCadastro(id: string): void {
		this.httpService.delete('ponto-turistico/' + id).subscribe({
			next: (response)=>{
				this.poNotification.success('Registro excluido com sucesso!');
				this.carregarPontoTuristico();
			},
			error: (error)=>{
				this.poNotification.error(error);
			}
		})
	}

}

interface PontoTuristicoLista{
	id: string;
    pais: string;
    cidade: string;
    nome: string;
    melhorEstacao: string;
}