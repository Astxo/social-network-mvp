package com.easydmarc.socialnetworkmvp.repository.impl;

import com.easydmarc.socialnetworkmvp.dto.CriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.PagingCriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.SearchCriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.SortingCriteriaDto;
import com.easydmarc.socialnetworkmvp.model.Friendship;
import com.easydmarc.socialnetworkmvp.model.User;
import com.easydmarc.socialnetworkmvp.repository.FriendshipRepositoryCustom;
import com.easydmarc.socialnetworkmvp.repository.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public class FriendshipRepositoryImpl implements FriendshipRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Friendship> getRandomFriendships(Integer userId, Integer limitCount) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friendship> query = cb.createQuery(Friendship.class);
        Root<Friendship> root = query.from(Friendship.class);
        query.multiselect(root.get("id"), root.get("userId"), root.get("friendId"), root.get("requestStatus"));

        Predicate p1 = cb.equal(root.get("userId"), userId);
        Predicate p2 = cb.equal(root.get("friendId"), userId);
        Predicate p3 = cb.equal(root.get("requestStatus"), "ACCEPTED");
        cb.and(p3);
        cb.or(p1, p2);
        query.orderBy(cb.asc(cb.function("RANDOM", Integer.class)));
        TypedQuery<Friendship> createdQuery = entityManager.createQuery(query);
        createdQuery.setFirstResult(0);
        createdQuery.setMaxResults(limitCount);
        return createdQuery.getResultList();
    }

}
