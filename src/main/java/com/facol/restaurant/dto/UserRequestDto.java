package com.facol.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// como estamos mandando coisa para o banco, não usamos record
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
}
