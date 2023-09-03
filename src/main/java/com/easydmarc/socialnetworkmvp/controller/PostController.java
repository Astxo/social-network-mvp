package com.easydmarc.socialnetworkmvp.controller;

import com.easydmarc.socialnetworkmvp.dto.CriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.PostDto;
import com.easydmarc.socialnetworkmvp.model.Post;
import com.easydmarc.socialnetworkmvp.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/getByCriteria/{userId}")
    @PreAuthorize("@authorizationService.isSameUser(#userId)")
    public List<Post> getByCriteria(@PathVariable Integer userId, @RequestBody CriteriaDto criteria) {
        List<Post> posts = postService.loadUserFeedByCriteria(criteria, userId);
        return posts;
    }

    @PostMapping("")
    @PreAuthorize("@authorizationService.isSameUser(#postDto.ownerId()) and @authorizationService.checkCreatePostAccess(#postDto.ownerId(), #postDto.friendId())")
    public ResponseEntity<Post> createPost(
            @RequestBody PostDto postDto
    ) {
        Post post = postService.createPost(postDto);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{postId}")
    @PreAuthorize("@authorizationService.checkManagePostAccess(#postId)")
    public ResponseEntity<Post> updatePost( @PathVariable Integer postId, @RequestBody PostDto postDto) {
        Post post = postService.updatePost(postId, postDto);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("@authorizationService.checkManagePostAccess(#postId)")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully. ID: " + postId);
    }

}
