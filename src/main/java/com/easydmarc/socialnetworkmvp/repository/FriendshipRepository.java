package com.easydmarc.socialnetworkmvp.repository;

import com.easydmarc.socialnetworkmvp.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer>, FriendshipRepositoryCustom {

  @Query(value = "SELECT f.id, f.user_id, f.friend_id, f.request_status FROM Friendship f " +
          "WHERE (f.user_id = :userId AND f.friend_id = :friendId) " +
          "OR (f.user_id = :friendId AND f.friend_id = :userId)",
          nativeQuery = true)
  Optional<Friendship> findByUserIdAndFriendId(@Param("userId") Integer userId,
                                               @Param("friendId") Integer friendId);

}
