package com.easydmarc.socialnetworkmvp.service.impl;

import com.easydmarc.socialnetworkmvp.helper.CurrentUserProvider;
import com.easydmarc.socialnetworkmvp.model.Friendship;
import com.easydmarc.socialnetworkmvp.model.Post;
import com.easydmarc.socialnetworkmvp.service.FriendshipService;
import com.easydmarc.socialnetworkmvp.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorizationService {

    private final CurrentUserProvider currentUserProvider;

    private final FriendshipService friendshipService;

    private final PostService postService;

    public AuthorizationService(CurrentUserProvider currentUserProvider,
                                FriendshipService friendshipService,
                                PostService postService) {
        this.currentUserProvider = currentUserProvider;
        this.friendshipService = friendshipService;
        this.postService = postService;
    }

    public boolean isSameUser(Integer userId) {
        return Objects.equals(currentUserProvider.getCurrentUserId(), userId);
    }

    public boolean checkCreatePostAccess(Integer ownerId, Integer friendId) {
        Friendship friendship = friendshipService.getByUserIdOrFriendId(ownerId, friendId);
        return friendship != null;
    }

    public boolean checkManagePostAccess(Integer postId) {
        Post post = postService.loadById(postId);
        boolean isSameUser = isSameUser(post.getOwnerId());
        return isSameUser;
    }
}
