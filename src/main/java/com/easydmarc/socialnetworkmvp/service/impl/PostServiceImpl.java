package com.easydmarc.socialnetworkmvp.service.impl;

import com.easydmarc.socialnetworkmvp.dto.CriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.PostDto;
import com.easydmarc.socialnetworkmvp.model.Post;
import com.easydmarc.socialnetworkmvp.repository.PostRepository;
import com.easydmarc.socialnetworkmvp.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(PostDto post) {

        Post newPost = new Post();
        newPost.setCreatedOn(new Date());
        newPost.setOwnerId(post.ownerId());
        newPost.setFriendId(post.friendId());
        newPost.setContent(post.content());

        return postRepository.save(newPost);
    }

    @Override
    public Post updatePost(Integer postId, PostDto postDto) {
        Optional<Post> optionalPost = postRepository.findById(postId);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setUpdatedOn(new Date());
            post.getContent().putAll(postDto.content());
            return postRepository.save(post);
        } else {
            throw new EntityNotFoundException("Post not found with ID: " + postId);
        }
    }

    @Override
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> loadUserFeedByCriteria(CriteriaDto criteria, Integer userId) {
        return postRepository.loadUserFeedByCriteria(criteria, userId);
    }

    @Override
    public Post loadById(Integer postId) {
        return postRepository.getReferenceById(postId);
    }
}
