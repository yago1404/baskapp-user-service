package com.baskapp.baskappsocial.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {
    @NotBlank(message = "E-mail é obrigatório")
    @Pattern(regexp = "(?i)^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Este e-mail não é válido")
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    private String password;
}
