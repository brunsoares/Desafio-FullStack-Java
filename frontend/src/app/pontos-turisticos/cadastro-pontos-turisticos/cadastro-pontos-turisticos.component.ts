import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PoNotificationService } from '@po-ui/ng-components';
import { HttpService } from 'src/app/service/http-service.service';
import { triggerFormValidators } from 'src/app/shared/util';

@Component({
  selector: 'app-cadastro-pontos-turisticos',
  templateUrl: './cadastro-pontos-turisticos.component.html',
  styleUrls: ['./cadastro-pontos-turisticos.component.css']
})
export class CadastroPontosTuristicosComponent implements OnInit {
	constructor(private formBuilder: FormBuilder,
		private poNotification: PoNotificationService,
		private route: ActivatedRoute,
		private router: Router,
		private http: HttpService
		) { 

	}

	ngOnInit(): void {
		console.log("Acessou")
	}

}
