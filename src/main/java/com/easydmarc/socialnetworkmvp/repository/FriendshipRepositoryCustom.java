package com.easydmarc.socialnetworkmvp.repository;

import com.easydmarc.socialnetworkmvp.model.Friendship;

import java.util.List;

public interface FriendshipRepositoryCustom {

    List<Friendship> getRandomFriendships(Integer userId, Integer limitCount);
}
