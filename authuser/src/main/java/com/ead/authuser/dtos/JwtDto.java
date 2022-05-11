package com.ead.authuser.dtos;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor //apenas campos obrigatorios no caso apenas o token por conta do @NonNull
public class JwtDto {

    @NonNull
    private String token;

    private String type = "Bearer";

}
