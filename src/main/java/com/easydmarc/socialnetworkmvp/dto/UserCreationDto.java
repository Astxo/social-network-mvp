package com.easydmarc.socialnetworkmvp.dto;

import jakarta.validation.constraints.NotNull;

public record UserCreationDto(@NotNull String firstName,
                              @NotNull String lastName,
                              @NotNull String email,
                              @NotNull String password) {
}
