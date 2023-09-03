import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadastro-comentario',
  templateUrl: './cadastro-comentario.component.html',
  styleUrls: ['./cadastro-comentario.component.css']
})
export class CadastroComentarioComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log("Acessou aqui!")
  }

}
