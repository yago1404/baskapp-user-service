package com.baskapp.baskappsocial.data.dtos.request;

import com.baskapp.baskappsocial.data.models.enums.PlayerPosition;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileDto {
    @Pattern(regexp = "^(\\S+\\s+\\S+.*)$", message = "O nome deve conter pelo menos nome e sobrenome")
    private String name;

    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(9\\d{4})[-.\\s]?(\\d{4})$", message = "Celular deve estar no formato valido")
    private String cellphone;

    @Past(message = "Data de nascimento deve estar no passado")
    private Date birthday;

    private int height;

    private PlayerPosition position;

    private Boolean open;
}
