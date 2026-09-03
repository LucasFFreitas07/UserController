package com.lff.connector.dto;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserUpdateRequest(
    Optional<String> first_name,
    Optional<String> last_name,
    Optional<String> login,
    Optional<String> email
) {

}
