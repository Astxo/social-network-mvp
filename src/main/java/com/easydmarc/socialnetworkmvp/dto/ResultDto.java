package com.easydmarc.socialnetworkmvp.dto;

import java.util.List;

public record ResultDto(boolean success, List<String> errors) {
}