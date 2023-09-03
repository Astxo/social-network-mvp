package com.easydmarc.socialnetworkmvp.repository;

import com.easydmarc.socialnetworkmvp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom {
}
