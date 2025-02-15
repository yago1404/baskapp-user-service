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