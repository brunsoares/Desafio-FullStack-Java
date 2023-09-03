# Documentação do Controlador de Comentários

O controlador `ComentarioController` é responsável por gerenciar as operações relacionadas aos comentários em nossa aplicação. Importante ressaltar, essa chamada está vinculada ao `PontoTuristicoController`, necessário efetuar cadastro do ponto turístico para comentar sobre.

## Endpoints

### 1. Criar um Comentário

- **Método HTTP:** POST
- **Endpoint:** /comentario
- **Descrição:** Cria um novo comentário com base nos dados fornecidos.

Exemplo de corpo da solicitação JSON:
```json
{
  "nome": "João",
  "comentario": "Este lugar é incrível!",
  "data": "03/09/2023",
  "pontoTuristico": {
    "id": 1
  }
}
```

### 2. Listar Todos os Comentários

- **Método HTTP:** GET
- **Endpoint:** /comentario
- **Descrição:** Retorna uma lista de todos os comentários cadastrados na aplicação.

### 3. Listar Comentários por Ponto Turístico

- **Método HTTP:** GET
- **Endpoint:** /comentario/{idPontoTuristico}/ponto-turistico
- **Descrição:** Retorna uma lista de comentários com base no ID do ponto turístico.
- **Exemplo de endpoint:** /comentario/1/ponto-turistico

### 4. Buscar um Comentário por ID

- **Método HTTP:** GET
- **Endpoint:** /comentario/{idComentario}
- **Descrição:** Retorna os detalhes de um comentário com base no ID fornecido.
- **Exemplo de endpoint:** /comentario/1

### 5. Excluir um Comentário
- **Método HTTP:** DELETE
- **Endpoint:** /comentario/{idComentario}
- **Descrição:** Exclui um comentário com base no ID fornecido.
- **Exemplo de endpoint:** /comentario/1

### 6. Atualizar um Comentário
- **Método HTTP:** PUT
- **Endpoint:** /comentario/{idComentario}
- **Descrição:** Atualiza os detalhes de um comentário com base no ID fornecido e nos dados fornecidos no corpo da solicitação JSON.
- 
Exemplo de corpo da solicitação JSON:
```json
{
  "nome": "Maria",
  "comentario": "Este lugar é incrível mesmo!",
  "data": "04/09/2023",
  "pontoTuristico": {
    "id": 1
  }
}
```

### Status de Resposta
- **201 Created:** Retornado após a criação bem-sucedida de um comentário.
- **200 OK:** Retornado para solicitações de busca ou atualização bem-sucedidas.
- **204 No Content:** Retornado após a exclusão bem-sucedida de um comentário.
- **400 Bad Request:** Retornado se houver problemas nos dados enviados na solicitação.
- **404 Not Found:** Retornado se um comentário não for encontrado com base no ID fornecido.
