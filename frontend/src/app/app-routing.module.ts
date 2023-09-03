import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComentarioComponent } from './comentarios/cadastro-comentario/cadastro-comentario.component';
import { CadastroPaisesComponent } from './paises/cadastro-paises/cadastro-paises.component';
import { PaisesComponent } from './paises/paises.component';
import { CadastroPontosTuristicosComponent } from './pontos-turisticos/cadastro-pontos-turisticos/cadastro-pontos-turisticos.component';
import { PontosTuristicosComponent } from './pontos-turisticos/pontos-turisticos.component';
import { ComentariosComponent } from './comentarios/comentarios.component';

const routes: Routes = [
  {
    path: 'pais',
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: PaisesComponent
      },
      {
        path: 'cadastro',
        children: [
          {
            path: ':idPais',
            component: CadastroPaisesComponent,
            data: { tipoCadastroPais: '' }
          },
          {
            path: '',
            pathMatch: 'full',
            component: CadastroPaisesComponent,
            data: { tipoCadastroPais: 'new' }
          }
        ]
      }
    ]
  },
  {
    path: 'ponto-turistico',
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: PontosTuristicosComponent,
      },
      {
        path: 'cadastro',
        children:[
          {
            path: '',
            pathMatch: 'full',
            component: CadastroPontosTuristicosComponent,
            data: { tipoCadastroPontoTuristico: 'new' }
          },
          {
            path: ':idPontoTuristico',
            pathMatch: 'full',
            component: CadastroPontosTuristicosComponent,
            data: { tipoCadastroPontoTuristico: 'view' },
          }
        ]
      }
    ]
  },
  {
    path: 'ponto-turistico/cadastro/:idPontoTuristico/comentario',
    pathMatch: 'full',
    component: ComentariosComponent,
    data: { tipoCadastroComentario: 'new' }
  },
  {
    path: 'ponto-turistico/cadastro/:idPontoTuristico/comentario/:idComentario',
    pathMatch: 'full',
    component: CadastroComentarioComponent,
    data: { tipoCadastroComentario: 'new' }
  },
  {
    path: "**",
    redirectTo: "pais"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
