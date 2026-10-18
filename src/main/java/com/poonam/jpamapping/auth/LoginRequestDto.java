package com.poonam.jpamapping.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {
   @NotBlank(message = "Username is required")
    private String  username;

   @NotBlank(message = "Password is required")
    private String password;
}
