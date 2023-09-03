package com.easydmarc.socialnetworkmvp.service.impl;

import com.easydmarc.socialnetworkmvp.model.Friendship;
import com.easydmarc.socialnetworkmvp.model.RequestStatus;
import com.easydmarc.socialnetworkmvp.model.User;
import com.easydmarc.socialnetworkmvp.repository.FriendshipRepository;
import com.easydmarc.socialnetworkmvp.service.FriendshipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;

    public FriendshipServiceImpl(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public void sendFriendRequest(Integer senderId, Integer toId) {

        Friendship existingRequest = getByUserIdOrFriendId(senderId, toId);

        if (existingRequest == null) {
            Friendship friendship = new Friendship(senderId, toId, RequestStatus.PENDING);
            friendshipRepository.save(friendship);
        }
    }

    @Override
    public void acceptFriendRequest(Integer id) {
        Friendship friendship = friendshipRepository.findById(id).orElse(null);
        if (friendship == null || friendship.getRequestStatus() != RequestStatus.PENDING) {
            return;
        }
        friendship.setRequestStatus(RequestStatus.ACCEPTED);
        friendshipRepository.save(friendship);
    }

    @Override
    public void rejectFriendRequest(Integer friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId).orElse(null);
        if (friendship == null || friendship.getRequestStatus() != RequestStatus.PENDING) {
            return;
        }
        friendshipRepository.deleteById(friendshipId);
    }

    @Override
    public Friendship getByUserIdOrFriendId(Integer userId, Integer friendId) {
        return friendshipRepository.findByUserIdAndFriendId(userId, friendId).orElse(null);
    }

    @Override
    public List<Friendship> getRandomOrderedFriends(Integer userId, Integer count) {
        return friendshipRepository.getRandomFriendships(userId, count);
    }
}
