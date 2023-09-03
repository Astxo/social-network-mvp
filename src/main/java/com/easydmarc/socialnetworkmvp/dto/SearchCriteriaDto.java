package com.easydmarc.socialnetworkmvp.dto;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public record SearchCriteriaDto(@Nullable String searchToken, @Nonnull Integer languageId) {

}
