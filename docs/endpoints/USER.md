# /user
Endpoints dedicados a manipular os usuários e acessos

---

## POST
Cria usuário de acesso

**body**
```JSON
{
  "email": "teste@teste.com",
  "password": "Abc123!"
}
```

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

Email já cadastrado (409):
```JSON
{
  "timestamp": "2025-02-08T22:50:51.680+00:00",
  "status": 409,
  "error": "Conflict",
  "message": "Email já cadastrado",
  "path": "/user"
}
```

---

## POST /login

Realiza autenticação do usuário

**body**
```JSON
{
  "email": "teste@teste.com",
  "password": "Abc123!"
}
```

**Sucesso**

```JSON
{
    "message": "success",
    "statusCode": 200,
    "data": {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1N2Y5NTZhYy1jNzE4LTQ4NmItOTM4NS04OGFjOTMwMDgzNjAiLCJleHAiOjE4MjU0NTE1MjJ9.aHBSOOwR5PtIsLfN7CsCXodGKSpC1IuA0-yyK7NU3O8",
        "refreshToken": "49227dee-0a62-4c23-9c27-05dfe480184f"
    }
}
```

**Erros**

Email ou senha invalidos (400):

```JSON
{
  "status": 400,
  "message": "Este e-mail não é válido\nSenha é obrigatório",
  "error": "Bad Request"
}
```

Email ou senha incorretos (404):

```JSON
{
  "timestamp": "2025-02-08T22:57:45.828+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Email ou senha incorretos",
  "path": "/user/login"
}
```