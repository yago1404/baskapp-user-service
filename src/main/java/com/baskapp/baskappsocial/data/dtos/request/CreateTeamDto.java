package com.baskapp.baskappsocial.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTeamDto {
    @NotNull(message = "Nome do time é obrigatório")
    String name;


}
