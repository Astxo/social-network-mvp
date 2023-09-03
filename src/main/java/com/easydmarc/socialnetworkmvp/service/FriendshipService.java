package com.easydmarc.socialnetworkmvp.service;

import com.easydmarc.socialnetworkmvp.model.Friendship;
import com.easydmarc.socialnetworkmvp.model.User;

import java.util.List;

public interface FriendshipService {

    void sendFriendRequest(Integer senderId, Integer toId);

    void acceptFriendRequest(Integer friendshipId);

    void rejectFriendRequest(Integer friendshipId);

    Friendship getByUserIdOrFriendId(Integer userId, Integer friendId);

    List<Friendship> getRandomOrderedFriends(Integer userId, Integer count);

}
