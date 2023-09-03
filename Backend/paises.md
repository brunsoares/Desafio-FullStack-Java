# Documentação do Controlador de Países

O controlador `PaisController` é responsável por gerenciar as operações relacionadas aos países em nossa aplicação.

## Endpoints

### 1. Criar um País

- **Método HTTP:** POST
- **Endpoint:** /pais
- **Descrição:** Cria um novo país com base nos dados fornecidos.

Exemplo de corpo da solicitação JSON:
```json
{
  "nome": "Brasil",
  "sigla": "BRA",
  "continente": "América do Sul",
  "ddi": 55
}
```

### 2. Listar Todos os Países

- **Método HTTP:** GET
- **Endpoint:** /pais
- **Descrição:** Retorna uma lista de todos os países cadastrados na aplicação.

### 3. Listar Países por Continente

- **Método HTTP:** GET
- **Endpoint:** /pais/{continente}/continente
- **Descrição:** Retorna uma lista de países com base no continente fornecido.
- **Exemplo de endpoint:** /pais/América do Sul/continente

### 4. Buscar um País por ID

- **Método HTTP:** GET
- **Endpoint:** /pais/{idPais}
- **Descrição:** Retorna os detalhes de um país com base no ID fornecido.
- **Exemplo de endpoint:** /pais/1

### 5. Excluir um País
- **Método HTTP:** DELETE
- **Endpoint:** /pais/{idPais}
- **Descrição:** Exclui um país com base no ID fornecido.
- **Exemplo de endpoint:** /pais/1

### 6. Atualizar um País
- **Método HTTP:** PUT
- **Endpoint:** /pais/{idPais}
- **Descrição:** Atualiza os detalhes de um país com base no ID fornecido e nos dados fornecidos no corpo da solicitação JSON.
- 
Exemplo de corpo da solicitação JSON:
```json
{
  "nome": "Argentina",
  "sigla": "ARG",
  "continente": "América do Sul",
  "ddi": 54
}
```

### Status de Resposta
- **201 Created:** Retornado após a criação bem-sucedida de um país.
- **200 OK:** Retornado para solicitações de busca ou atualização bem-sucedidas.
- **204 No Content:** Retornado após a exclusão bem-sucedida de um país.
- **400 Bad Request:** Retornado se houver problemas nos dados enviados na solicitação.
- **404 Not Found:** Retornado se um país não for encontrado com base no ID fornecido.
