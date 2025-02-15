# Manual de documentações

---
As documentações dos endpoints possuem um padrão, são estruturados da seguinte maneira:

Cabeçalho, onde consta o prefixo dos endpoints daquele barramento

Ex.: /user

Em seguida são elencados os parametros da requisição, como body, headers, queryParams e assim por diante

Ex.:

**body**
```JSON
{
  "email": "teste@teste.com",
  "password": "Abc123!"
}
```

Por último são mostrados os possivéis retornos de sucesos e erro, junto com a explicação de campos especificos

Por padrão, retornos de suceso seguem a seguinte estrutura:
```JSON
{
  "message": "Messagem referente a ação <string>",
  "statusCode": "status de retorno <int>",
  "data": "json resultante da requisição <Map>"
}
```

Já status de erro tem o seguinte padrão
```JSON
{
  "message": "Messagem referente a ação <string>",
  "statusCode": "Status de retorno <int>",
  "error": "Erro equivalente ao statusCode <string>"
}
```

Dependendo do tipo de erro outros campos voltam na requisição com erro como timestamp e path, porém apenas os campos a cima são obrigatórios.

Ex.:

**Sucesso**

Usuário criado (201):
```JSON
{
  "message": "success",
  "statusCode": 201,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJkMDI5YzlmNS05ODFhLTQ1OWMtOTA3Ny1lZjI3NTY2YmU2YjUiLCJleHAiOjE4MjU0NTQ3ODR9.0xWMwJ_M3b9LjSUui69ZpGg5xPI6nb1OP182em8IBPw",
    "refreshToken": "f4bc1bc5-8330-449f-bca9-c019cf6feb94"
  }
}
```

- **token**: Token JWT utilizado nos headers das requisições autenticadas
- **refreshToken**: Token para refresh de seção, após expiração do JWT

**Erros**

Email ou senha invalidos (400):
```JSON
{
  "status": 400,
  "message": "Este e-mail não é válido\nSenha é obrigatório",
  "error": "Bad Request"
}
```
