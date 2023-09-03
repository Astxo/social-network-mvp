package com.easydmarc.socialnetworkmvp.dto;

import jakarta.validation.constraints.NotNull;

public record AuthDto(@NotNull String email, @NotNull String password) {

}
