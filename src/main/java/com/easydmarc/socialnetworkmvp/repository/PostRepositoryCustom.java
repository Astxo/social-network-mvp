package com.easydmarc.socialnetworkmvp.repository;

import com.easydmarc.socialnetworkmvp.dto.CriteriaDto;
import com.easydmarc.socialnetworkmvp.model.Post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> loadUserFeedByCriteria(CriteriaDto criteria, Integer userId);
}
