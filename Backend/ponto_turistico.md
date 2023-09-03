# Documentação do Controlador de Pontos Turísticos

O controlador `PontoTuristicoController` é responsável por gerenciar as operações relacionadas aos pontos turísticos em nossa aplicação. Importante ressaltar, essa chamada está relacionada ao `ComentarioController`, algumas funções afetam ambos.

## Endpoints

### 1. Criar um Ponto Turístico

- **Método HTTP:** POST
- **Endpoint:** /ponto-turistico
- **Descrição:** Cria um novo ponto turístico com base nos dados fornecidos.

Exemplo de corpo da solicitação JSON:
```json
{
  "cidade": "Rio de Janeiro",
  "nome": "Cristo Redentor",
  "melhorEstacao": "Verão",
  "pais": {
    "id": 1
  }
}
```

### 2. Listar Todos os Ponto Turístico

- **Método HTTP:** GET
- **Endpoint:** /ponto-turistico
- **Descrição:** Retorna uma lista de todos os pontos turísticos cadastrados na aplicação.

### 3. Listar Pontos Turísticos por País

- **Método HTTP:** GET
- **Endpoint:** /ponto-turistico/{idPais}/pais
- **Descrição:** Retorna uma lista de pontos turísticos com base no ID do país.
- **Exemplo de endpoint:** /ponto-turistico/1/pais

### 4. Listar Pontos Turísticos por Cidade

- **Método HTTP:** GET
- **Endpoint:** /ponto-turistico/{cidade}/cidade
- **Descrição:** Retorna uma lista de pontos turísticos com base no nome da cidade.
- **Exemplo de endpoint:** /ponto-turistico/Rio de Janeiro/cidade

### 5. Buscar um Ponto Turístico por ID

- **Método HTTP:** GET
- **Endpoint:** /ponto-turistico/{idPontoTuristico}
- **Descrição:** Retorna os detalhes de um ponto turístico com base no ID fornecido.
- **Exemplo de endpoint:** /ponto-turistico/1

### 6. Excluir um Ponto Turístico

- **Método HTTP:** DELETE
- **Endpoint:** /ponto-turistico/{idPontoTuristico}
- **Descrição:** xclui um ponto turístico com base no ID fornecido. Todos os comentários associados ao ponto turístico também serão excluídos.
- **Exemplo de endpoint:** /ponto-turistico/1

### 7. Atualizar um Ponto Turístico

- **Método HTTP:** PUT
- **Endpoint:** /ponto-turistico/{idPontoTuristico}
- **Descrição:** Atualiza os detalhes de um ponto turístico com base no ID fornecido e nos dados fornecidos no corpo da solicitação JSON.
  
Exemplo de corpo da solicitação JSON:
```json
{
  "cidade": "Rio de Janeiro",
  "nome": "Cristo Redentor",
  "melhorEstacao": "Inverno",
  "pais": {
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
