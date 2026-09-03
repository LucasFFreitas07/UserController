package com.lff.connector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRequest(
        @Size(max = 20) @NotBlank(message = "Primeiro nome não pode ser vazio") String first_name,
        @Size(max = 20) @NotBlank(message = "Segundo nome não pode ser vazio") String last_name,
        @Size(min=8, max = 8) @NotBlank(message = "Login não pode ser vazio") String login,
        @NotBlank(message = "E-mail não pode ser vazio") String email) {

}
