import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { HttpService } from 'src/app/service/http-service.service';
import { PoComboOption } from '@po-ui/ng-components';
import { PontoTuristico } from '../ponto-turistico';
import { Pais } from 'src/app/paises/paises';
import { Comentario } from 'src/app/comentarios/comentario';

@Component({
  selector: 'app-cadastro-pontos-turisticos',
  templateUrl: './cadastro-pontos-turisticos.component.html',
  styleUrls: ['./cadastro-pontos-turisticos.component.css']
})
export class CadastroPontosTuristicosComponent implements OnInit {
	idPontoTuristico: string;
	formPontoTuristico: FormGroup;
	title: string = "Novo cadastro de Ponto Turístico";
	paisesOptions: Array<PoComboOption>;
	pontoTuristicoSalvar: PontoTuristico;
	paisSalvar: Pais;
	comentariosSalvar: Array<Comentario>;

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

	ngOnInit(): void {
		this.paisesOptions = [];
		this.carregarPaises();

		this.idPontoTuristico = this.route.snapshot.paramMap.get('idPontoTuristico');
		if (this.idPontoTuristico !== null && this.idPontoTuristico !== ""){
			this.buscaDadosPontoTuristico()
			this.title = "Alteração do Ponto Turístico";
		}
	}

	carregarPaises(){
		return this.http.get('pais').subscribe({
			next: (resposta)=>{
				resposta.forEach(item => {
					this.paisesOptions = [...this.paisesOptions, {label: item.nome, value: item.id}];
				});
			}
		})
	}

	validarRegistro(): boolean{
		return this.formPontoTuristico.valid;
	}

	enviarPost(){
		this.pontoTuristicoSalvar = {
			id: "",
			pais: this.paisSalvar,
			cidade: this.formPontoTuristico.value.cidade,
			nome: this.formPontoTuristico.value.nome,
			melhorEstacao: this.formPontoTuristico.value.melhorEstacao,
			comentarios: []
		};

		this.http.post('ponto-turistico',this.pontoTuristicoSalvar).subscribe({
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
		this.pontoTuristicoSalvar = {
			id: "",
			pais: this.paisSalvar,
			cidade: this.formPontoTuristico.value.cidade,
			nome: this.formPontoTuristico.value.nome,
			melhorEstacao: this.formPontoTuristico.value.melhorEstacao,
			comentarios: this.comentariosSalvar
		};

		this.http.put('ponto-turistico/' + this.idPontoTuristico,this.pontoTuristicoSalvar).subscribe({
			next:(resposta) => {
				this.poNotification.success("Registro atualizado com sucesso!");
				this.voltar();
			},
			error:(erro) => {
				this.poNotification.error(erro)
			},
		})
	}

	buscaDadosPontoTuristico(){
		this.http.get('ponto-turistico/' + this.idPontoTuristico).subscribe({
			next: (resposta)=>{
				this.formPontoTuristico.patchValue({
					nome: resposta.nome,
					pais: resposta.pais.id,
					cidade: resposta.cidade,
					melhorEstacao: resposta.melhorEstacao
				})
			},
			error: (erro)=>{
				this.poNotification.error(erro)
			}
		})
	}

	buscaDadosPais(idPais): Promise<void> {
		return new Promise<void>((resolve, reject) => {
		  this.http.get('pais/' + idPais).subscribe({
			next: (resposta) => {
			  this.paisSalvar = {
				id: resposta.id,
				nome: resposta.nome,
				sigla: resposta.sigla,
				continente: resposta.continente,
				codigoDdi: resposta.ddi,
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
	
	buscaDadosComentarios(): Promise<void> {
		return new Promise<void>((resolve, reject) => {
		  this.http.get('comentario/' + this.idPontoTuristico + "/ponto-turistico").subscribe({
			next: (resposta) => {
				let registros: Array<Comentario> = []
				resposta.forEach(item => {
					let comentarioExistente: Comentario = {
						id: item.id,
						nome: item.nome,
						comentario: item.comentario,
						data: item.data,
						pontoTuristico: item.pontoTuristico,
					}
					registros.push(comentarioExistente);
				});
				
				this.comentariosSalvar = [...registros]
				console.log(this.comentariosSalvar);

			  resolve();
			},
			error: (erro) => {
			  this.poNotification.error(erro);
			  reject(erro);
			}
		  });
		});
	}
	
	async salvar() {
		if (this.validarRegistro()) {
			try {
			await this.buscaDadosPais(this.formPontoTuristico.value.pais);
			if (this.idPontoTuristico === null || this.idPontoTuristico === "") {
				this.enviarPost()
			} else {
				await this.buscaDadosComentarios();
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
		this.router.navigate(['/ponto-turistico'], { relativeTo: this.route })
	}

} 
