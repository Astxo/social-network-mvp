package com.easydmarc.socialnetworkmvp.dto;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public record CriteriaDto(@Nonnull PagingCriteriaDto pagingCriteria,
                          @Nullable SortingCriteriaDto sortingCriteria,
                          @Nullable SearchCriteriaDto searchCriteria) {
}
