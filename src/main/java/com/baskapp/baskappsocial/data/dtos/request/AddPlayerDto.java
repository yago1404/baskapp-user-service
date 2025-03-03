package com.baskapp.baskappsocial.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPlayerDto {
    @NotNull(message = "Id do perfil é obrigatório")
    private UUID profileId;

    @NotNull(message = "Id do time é obrigatório")
    private UUID teamId;
}
