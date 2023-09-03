package com.easydmarc.socialnetworkmvp.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record PostDto(@NotNull Integer ownerId, @NotNull Integer friendId, @NotNull Map<String, String> content) {

}
