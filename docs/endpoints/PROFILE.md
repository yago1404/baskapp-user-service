# /profile
Endpoints dedicados a manipular os perfis dos usuários

---

## POST
Cria a associa um perfil a um usuário, podendo ser um perfil de jogador ou treinador

**headers**
```JSON
{
  "Authentication": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5NzBmMTAwNC1lMDUwLTRhNzgtYTY5ZC04ZTg0MjJhOGE4OTgiLCJleHAiOjE3Mzk3NDE3OTJ9.KyhHomsfqStpd3_4RY2OzUO65K7d8Z2WhDv6kTpxymc"
}
```

**body**

```JSON
{
    "name": "Jogador 1",
    "cellphone": "82988509560",
    "birthday": "2000-04-14",
    "height": "185",
    "position": "POINT_GUARD",
    "rule": "PLAYER"
}
```
Esse endpoint tem como paramtros obrigatórios: **name**, **birthday**, **rule** 

- **height**: Altura em CM do jogador
- **position**: Posição do jogador, podendo ser: POINT_GUARD, SHOOTING_GUARD, SMALL_FORWARD, POWER_FORWARD, CENTER
- **rule**: Tipo de perfil do usuário, podendo ser: **COACH** ou **PLAYER**

**Sucesso** (200)
```
{
    "message": "success",
    "statusCode": 200,
    "data": {
        "id": "0a7c8870-955e-4f99-99ce-12c80b7f1216",
        "name": "Jogador 1",
        "cellphone": "82988509560",
        "birthday": "2000-04-14T00:00:00.000+00:00",
        "height": 185,
        "position": "POINT_GUARD",
        "rule": "PLAYER",
        "picture": null,
        "open": false
    }
}
```

**Erros**

**Dados faltando** (400)
```JSON
{
  "status": 400,
  "message": "Nome é um atributo obrigatório\nData de nascimento é um atributo obrigatório\nAtribuição é um atributo obrigatório",
  "error": "Bad Request"
}
```

**Perfil ja associado a um usuário** (409)
```JSON
{
    "timestamp": "2025-02-15T21:40:57.257+00:00",
    "status": 409,
    "error": "Conflict",
    "message": "Esse usuário já possui um perfil",
    "path": "/profile"
}
```

---

## GET

Recupera o perfil do usuário

**headers**
```JSON
{
  "Authentication": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5NzBmMTAwNC1lMDUwLTRhNzgtYTY5ZC04ZTg0MjJhOGE4OTgiLCJleHAiOjE3Mzk3NDE3OTJ9.KyhHomsfqStpd3_4RY2OzUO65K7d8Z2WhDv6kTpxymc"
}
```

**Sucesso**
```JSON
{
    "message": "success",
    "statusCode": 200,
    "data": {
        "id": "767366c8-8598-4308-b57d-af3422d296eb",
        "name": "Coach 1",
        "cellphone": null,
        "birthday": "2000-04-14T00:00:00.000+00:00",
        "height": 0,
        "position": null,
        "rule": "COACH",
        "picture": null,
        "open": false
    }
}
```
- **open**: indica se um perfil está apto para ser divulgado
- **height**: caso venha com o valor 0, indica que a altura do usuário não foi informada, caso o contrario, representa a altura do perfil em centimetros

**Erros**

Usuário sem perfil associado (404)
```JSON
{
    "timestamp": "2025-02-15T23:07:18.558+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "Usuário não possui um perfil associado",
    "path": "/profile"
}
```

---

## PUT

Atualiza o perfil do usuário

**headers**
```JSON
{
  "Authentication": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5NzBmMTAwNC1lMDUwLTRhNzgtYTY5ZC04ZTg0MjJhOGE4OTgiLCJleHAiOjE3Mzk3NDE3OTJ9.KyhHomsfqStpd3_4RY2OzUO65K7d8Z2WhDv6kTpxymc"
}
```

**body**
```JSON
{
  "name": "Jogador Sobrenome",
  "cellphone":  "9999999999",
  "birthday":  "2000-11-22",
  "height":  180,
  "position": "POINT_GUARD",
  "open": false
}
```

Todos os campos são opcionais, deve mandar apenas o campo que deseja atualizar
- **height**: Altura em CM do jogador
- **position**: Posição do jogador, podendo ser: POINT_GUARD, SHOOTING_GUARD, SMALL_FORWARD, POWER_FORWARD, CENTER

**Sucesso**
```JSON
{
    "message": "success",
    "statusCode": 200,
    "data": {
        "id": "767366c8-8598-4308-b57d-af3422d296eb",
        "name": "Coach 1",
        "cellphone": null,
        "birthday": "2000-04-14T00:00:00.000+00:00",
        "height": 0,
        "position": null,
        "rule": "COACH",
        "picture": null,
        "open": false
    }
}
```
- **open**: indica se um perfil está apto para ser divulgado
- **height**: caso venha com o valor 0, indica que a altura do usuário não foi informada, caso o contrario, representa a altura do perfil em centimetros

**Erros**

Usuário sem perfil associado (404)
```JSON
{
  "timestamp": "2025-02-23T14:38:35.661+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Usuário não possui perfil",
  "path": "/profile"
}
```

**Dados inválidos** (400)
```JSON
{
  "status": 400,
  "message": "Data de nascimento deve estar no passado",
  "error": "Bad Request"
}
```
