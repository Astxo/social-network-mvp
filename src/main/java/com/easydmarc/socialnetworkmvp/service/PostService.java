package com.easydmarc.socialnetworkmvp.service;

import com.easydmarc.socialnetworkmvp.dto.CriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.PostDto;
import com.easydmarc.socialnetworkmvp.model.Post;

import java.util.List;
import java.util.Map;

public interface PostService {

    Post createPost(PostDto post);

    Post updatePost(Integer postId, PostDto postDto);

    void deletePost(Integer id);

    List<Post> loadUserFeedByCriteria(CriteriaDto criteria, Integer userId);

    Post loadById(Integer postId);

}
