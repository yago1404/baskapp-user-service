package com.baskapp.baskappsocial.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class RefreshTokenDto {
    @NotBlank(message = "RefreshToen é obrigatório")
    private String refreshToken;
}
