package com.plukash.testuserservice.entities.DTO.Auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//TODO validations
public class AuthPhoneRequest {
    private String phone;

    private String password;
}
