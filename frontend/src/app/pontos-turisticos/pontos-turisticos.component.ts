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

  constructor(
		private router: Router,
		private activatedRoute: ActivatedRoute,
    private httpService: HttpService,
		private poNotification: PoNotificationService
  ) { }

  ngOnInit(): void {
  }

  navegarParaCadastro(codigoPontoTuristico: string = ""){
	  this.router.navigate(['cadastro', codigoPontoTuristico], { relativeTo: this.activatedRoute })
	}

  carregarActions(): Array<PoTableAction> {
		return [
			{
				label: 'Editar',
				icon: 'po-icon-edit'
			},
			{
				label: 'Excluir',
				icon: 'po-icon-delete'
			},
      {
        label: 'Detalhes',
        icon: 'po-icon-clipboard'
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

}

interface PontoTuristico{
	id: string,
	nome: string,
	pais: string,
  cidade: string,
	melhorEstacao: string,
}
