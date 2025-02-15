package com.baskapp.baskappsocial.data.dtos.request;

import com.baskapp.baskappsocial.data.models.enums.PlayerPosition;
import com.baskapp.baskappsocial.data.models.enums.UserRule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateProfileDto {
    @NotBlank(message = "Nome é um atributo obrigatório")
    private String name;

    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(9\\d{4})[-.\\s]?(\\d{4})$")
    private String cellphone;

    @NotNull(message = "Data de nascimento é um atributo obrigatório")
    @Past(message = "Data de nascimento deve estar no passado")
    private Date birthday;

    private int height;

    private PlayerPosition position;

    @NotNull(message = "Atribuição é um atributo obrigatório")
    private UserRule rule;
}
