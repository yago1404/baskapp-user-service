package com.baskapp.baskappsocial.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTeamDto {
    @NotBlank(message = "Nome do time é obrigatório")
    String name;


}
