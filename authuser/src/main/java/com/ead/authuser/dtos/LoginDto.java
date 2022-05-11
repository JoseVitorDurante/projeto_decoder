package com.ead.authuser.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
