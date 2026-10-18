package com.poonam.jpamapping.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {

    private String token;

    private String username;

    private String role;
}
