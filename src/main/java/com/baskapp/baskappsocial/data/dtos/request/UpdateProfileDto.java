package com.baskapp.baskappsocial.data.dtos.request;

import com.baskapp.baskappsocial.data.models.enums.PlayerPosition;
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
    private String name;

    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?(9\\d{4})[-.\\s]?(\\d{4})$")
    private String cellphone;

    private Date birthday;

    private int height;

    private PlayerPosition position;

    private Boolean open;
}
