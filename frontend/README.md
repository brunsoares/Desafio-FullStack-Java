# Frontend - Desafio-Fullstack-Java

Este projeto é o Front do sistema de Pontos turísticos. Desempenhando o papel de executar as chamadas criadas na API pelo visual do Client. Abaixo temos algumas rotas para cada componente e suas respectivas funções.

## Rota de Países
Esta rota lida com o cadastro e gerenciamento de países. Pode ser acessada pelo menu lateral.

### Lista de Países
- **PaisesComponent:** Exibe a lista de países cadastrados.
- **Rota:** `http://localhost:4200/pais`

### Rota de Cadastro
- **CadastroPaisesComponent:** Permite criar ou editar países.
- **Rota:** `http://localhost:4200/pais/:idPais`
  - /:idPais: Edita um país existente.
  - /: Cria um novo país.
    
## Rota de Pontos Turísticos
Esta rota lida com o cadastro e gerenciamento de pontos turísticos. Pode ser acessada pelo menu lateral.

### Lista de Pontos Turísticos
- **PontosTuristicosComponent:** Exibe a lista de pontos turísticos cadastrados.
- **Rota:** `http://localhost:4200/ponto-turistico`
  
### Rota de Cadastro
- **CadastroPontosTuristicosComponent:** Permite criar ou editar pontos turísticos.
- **Rota:** `http://localhost:4200/ponto-turistico/:idPontoTuristico`
  - /:idPontoTuristico: Edita um ponto turístico existente.
  - /: Cria um novo ponto turístico. 

## Rota de Comentários
Esta rota lida com o cadastro e gerenciamento de comentários para pontos turísticos. Pode ser acessada ao clicar em `Detalhes` de cada Ponto Turístico.

### Lista de Comentários e resumo do Ponto Turístico
- **ComentariosComponent:** Exibe a lista de comentários para um ponto turístico.
- **Rota:** `http://localhost:4200/ponto-turistico/cadastro/:idPontoTuristico/comentario`

### Rota de Cadastro de Comentário
- **CadastroComentarioComponent:** Permite criar ou editar comentários.
- **Rota:** `http://localhost:4200/ponto-turistico/cadastro/:idPontoTuristico/comentario/:idComentario`
  - /0: Cria um novo comentário vinculado a um ponto turístico específico.
  - /:idComentario: Edita um comentário existente vinculado a um ponto turístico.

<hr/>

### Pré-requisitos

Para a execução deste projeto é necessário que a maquina tenha o seguintes sistemas instalados:

- [Node](https://nodejs.org/en/download/). 
  - Aconselhamos utilizar a [versão 16.19.1](https://nodejs.org/download/release/v16.19.1/).
- [NPM](https://www.npmjs.com/)
  - Será instalado junto do Node
- [Angular](https://angular.io/)
  - Siga o guia para instalar o [angular-cli](https://angular.io/guide/setup-local) corretamente

Após a instalação dos sistemas não esqueça de realizar a instalação das dependências.

Outro ponto importante é quando for rodar o projeto utilize o comando `npm start` inves do `ng serve`

### Rodando o projeto

O projeto já esta integrando com o Back-End, lembre-se de sempre estar com os dois projetos rodando para realizar o desenvolvimento.
