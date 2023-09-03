package com.easydmarc.socialnetworkmvp.controller;

import com.easydmarc.socialnetworkmvp.model.Friendship;
import com.easydmarc.socialnetworkmvp.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @Autowired
    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendFriendRequest(@RequestParam Integer senderId,
                                                        @RequestParam Integer toId) {
        friendshipService.sendFriendRequest(senderId, toId);
        return ResponseEntity.ok("Successfully sent!");
    }

    @PostMapping("/accept/{id}") // TODO Add Authorization check
    public ResponseEntity<String> acceptFriendRequest(@PathVariable Integer id) {
        friendshipService.acceptFriendRequest(id);
        return ResponseEntity.ok("Accepted!");
    }

    @PostMapping("/reject/{id}") // TODO Add Authorization check
    public ResponseEntity<String> rejectFriendRequest(@PathVariable Integer id) {
        friendshipService.rejectFriendRequest(id);
        return ResponseEntity.ok("Rejected");
    }

    @GetMapping("/{id}/friends")
    public List<Friendship> loadUserFriends(@PathVariable Integer id) {
        List<Friendship> friendships = friendshipService.getRandomOrderedFriends(id, 10); //TODO After loading friendships, corresponding Friends should be loaded from Users table
        return friendships;
    }

}
