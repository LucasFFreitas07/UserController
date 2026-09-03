package com.lff.connector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRequest(
        @Size(max = 20) String first_name,
        @Size(max = 20) String last_name,
        @Size(min=8, max = 8) String login,
        String email) {

}
