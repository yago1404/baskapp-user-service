# /team

Endpoints dedicados a manipular os times

---

## POST

Cria um time e associa o usuário logado como tecnico

**headers**

```JSON
{
  "Authentication": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5NzBmMTAwNC1lMDUwLTRhNzgtYTY5ZC04ZTg0MjJhOGE4OTgiLCJleHAiOjE3Mzk3NDE3OTJ9.KyhHomsfqStpd3_4RY2OzUO65K7d8Z2WhDv6kTpxymc"
}
```

**body**

```JSON
{
  "name": "Lakers"
}
```

Esse endpoint tem como paramtros obrigatórios: **name**

**Sucesso** (201)

```JSON
{
  "message": "created",
  "statusCode": 201,
  "data": {
    "id": "c9af12d1-1ee8-49db-b2bb-3056c53b6f4c",
    "name": "Lakers",
    "players": [],
    "coach": {
      "id": "68a8cda3-b69d-4f65-b3ac-28ef85e56e6e",
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
}
```

**Erros**

**Dados faltando** (400)

```JSON
{
  "status": 400,
  "message": "Nome do time é obrigatório",
  "error": "Bad Request"
}
```

**Perfil logado não é do tipo COACH** (403)

```JSON
{
  "timestamp": "2025-03-03T19:30:02.574+00:00",
  "status": 403,
  "error": "Forbidden",
  "message": "É preciso ser um técnico para criar um time",
  "path": "/team"
}
```

---

## GET

Retorna a lista de times que o usuário logado esta associado como tecnico

**headers**

```JSON
{
  "Authentication": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5NzBmMTAwNC1lMDUwLTRhNzgtYTY5ZC04ZTg0MjJhOGE4OTgiLCJleHAiOjE3Mzk3NDE3OTJ9.KyhHomsfqStpd3_4RY2OzUO65K7d8Z2WhDv6kTpxymc"
}
```

**Sucesso** (201)

```JSON
{
  "message": "success",
  "statusCode": 200,
  "data": {
    "teams": [
      {
        "id": "e3be4fee-2125-4960-a7eb-8dd538b18ae7",
        "name": "Time 3",
        "players": [
          {
            "id": "0a7c8870-955e-4f99-99ce-12c80b7f1216",
            "name": "Jogador 1",
            "cellphone": "82988509560",
            "birthday": "2000-04-14T00:00:00.000+00:00",
            "height": 185,
            "position": "POINT_GUARD",
            "rule": "PLAYER",
            "picture": null,
            "open": true
          }
        ],
        "coach": {
          "id": "767366c8-8598-4308-b57d-af3422d296eb",
          "name": "Coach 2",
          "cellphone": null,
          "birthday": "2000-04-14T00:00:00.000+00:00",
          "height": 0,
          "position": null,
          "rule": "COACH",
          "picture": null,
          "open": true
        }
      }
    ]
  }
}
```

**Erros**

**Sem times associados ao perfil** (404)

```JSON
{
  "timestamp": "2025-03-03T19:52:04.286+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Sem times associados ao perfil",
  "path": "/team/my-teams"
}
```

## POST /player

Associa jogador a um time

**headers**

```JSON
{
  "Authentication": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5NzBmMTAwNC1lMDUwLTRhNzgtYTY5ZC04ZTg0MjJhOGE4OTgiLCJleHAiOjE3Mzk3NDE3OTJ9.KyhHomsfqStpd3_4RY2OzUO65K7d8Z2WhDv6kTpxymc"
}
```

**body**

```JSON
{
  "teamId": "e3be4fee-2125-4960-a7eb-8dd538b18ae7",
  "profileId": "767366c8-8598-4308-b57d-af3422d296eb"
}
```

Esse endpoint tem como paramtros obrigatórios: **teamId** e **profileId**

- **teamId**: UUID do time
- **profileId**: UUID do perfil de um PLAYER

**Sucesso** (200)

```JSON
{
  "message": "success",
  "statusCode": 200,
  "data": {
    "id": "c9af12d1-1ee8-49db-b2bb-3056c53b6f4c",
    "name": "Celtics",
    "players": [
      {
        "id": "73632e20-368d-4eec-bc25-67de611fc381",
        "name": "Jogador 1",
        "cellphone": "82988509560",
        "birthday": "2000-04-14T00:00:00.000+00:00",
        "height": 185,
        "position": "POINT_GUARD",
        "rule": "PLAYER",
        "picture": null,
        "open": true
      }
    ],
    "coach": {
      "id": "68a8cda3-b69d-4f65-b3ac-28ef85e56e6e",
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
}
```

**Erros**

**Dados faltando** (400)

```JSON
{
  "status": 400,
  "message": "Id do time é obrigatório",
  "error": "Bad Request"
}
```

**Jogador ja esta associado ao time** (403)

```JSON
{
  "timestamp": "2025-03-03T22:19:30.071+00:00",
  "status": 409,
  "error": "Conflict",
  "message": "Esse jogador já esta associado ao time",
  "path": "/team/player"
}
```

**Time nao encontrado** (404)

```JSON
{
  "timestamp": "2025-03-03T22:20:00.850+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Time não encontrado",
  "path": "/team/player"
}
```

**Jogador nao encontrado** (404)

```JSON
{
  "timestamp": "2025-03-03T22:20:36.373+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Perfil do jogador não encontrado",
  "path": "/team/player"
}
```

**Tecnico nao pode ser jogador** (409)

```JSON
{
  "timestamp": "2025-03-03T22:21:15.435+00:00",
  "status": 409,
  "error": "Conflict",
  "message": "Um tecnico nao pode ser associado como jogador",
  "path": "/team/player"
}
```

---

## PUT /{teamId}

Atualiza informacoes do time

**headers**

```JSON
{
  "Authentication": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5NzBmMTAwNC1lMDUwLTRhNzgtYTY5ZC04ZTg0MjJhOGE4OTgiLCJleHAiOjE3Mzk3NDE3OTJ9.KyhHomsfqStpd3_4RY2OzUO65K7d8Z2WhDv6kTpxymc"
}
```

**body**

```JSON
{
  "name": "Lakers",
  "coachId": "767366c8-8598-4308-b57d-af3422d296eb"
}
```

- **coachId**: UUID do perfil de um COACH

**Sucesso** (200)

```JSON
{
  "message": "success",
  "statusCode": 200,
  "data": {
    "id": "c9af12d1-1ee8-49db-b2bb-3056c53b6f4c",
    "name": "Lakers",
    "players": [
      {
        "id": "73632e20-368d-4eec-bc25-67de611fc381",
        "name": "Jogador 1",
        "cellphone": "82988509560",
        "birthday": "2000-04-14T00:00:00.000+00:00",
        "height": 185,
        "position": "POINT_GUARD",
        "rule": "PLAYER",
        "picture": null,
        "open": true
      }
    ],
    "coach": {
      "id": "68a8cda3-b69d-4f65-b3ac-28ef85e56e6e",
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
}
```

**Erros**

**So pode atualizar se for coach do time** (403)

```JSON
{
  "timestamp": "2025-03-03T22:26:58.258+00:00",
  "status": 403,
  "error": "Forbidden",
  "message": "É preciso ser o tecnico do time para atualiza-lo",
  "path": "/team/27fcab22-97c2-4214-af90-3d2d01fe3d22"
}
```

**Time nao encontrado** (404)

```JSON
{
  "timestamp": "2025-03-03T22:27:45.535+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Time não encontrado",
  "path": "/team/27fcab22-97c2-4214-af90-3d2d01fe3d21"
}
```

**Perfil do tecnico nao encontrado** (404)

```JSON
{
  "timestamp": "2025-03-03T22:29:19.445+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Técnico não encontrado",
  "path": "/team/c9af12d1-1ee8-49db-b2bb-3056c53b6f4c"
}
```
