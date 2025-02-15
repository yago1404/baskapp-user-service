# /profile
Endpoints dedicados a manipular os perfis dos usuários

---

## POST
Cria a associa um perfil a um usuário, podendo ser um perfil de jogador ou treinador

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