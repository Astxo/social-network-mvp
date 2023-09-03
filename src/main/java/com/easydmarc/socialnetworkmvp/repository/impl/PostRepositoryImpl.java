package com.easydmarc.socialnetworkmvp.repository.impl;

import com.easydmarc.socialnetworkmvp.dto.CriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.PagingCriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.SearchCriteriaDto;
import com.easydmarc.socialnetworkmvp.dto.SortingCriteriaDto;
import com.easydmarc.socialnetworkmvp.model.Post;
import com.easydmarc.socialnetworkmvp.repository.PostRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class PostRepositoryImpl implements PostRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> loadUserFeedByCriteria(CriteriaDto criteria, Integer userId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> query = cb.createQuery(Post.class);
        Root<Post> root = query.from(Post.class);
        query.multiselect(root.get("id"), root.get("ownerId"), root.get("friendId"), root.get("content"), root.get("createdOn"));
        applySearchCriteria(criteria.searchCriteria(), cb, query, root);
        applySortingCriteria(criteria.sortingCriteria(), cb, query, root);
        TypedQuery<Post> createdQuery = entityManager.createQuery(query);
        applyPagingCriteria(criteria.pagingCriteria(), createdQuery);

        return createdQuery.getResultList();
    }

    private void applySearchCriteria(SearchCriteriaDto searchCriteriaDto,
                                     CriteriaBuilder cb,
                                     CriteriaQuery<Post> query,
                                     Root<Post> root) {

        if (searchCriteriaDto == null) return;

        if (searchCriteriaDto.searchToken() != null && !searchCriteriaDto.searchToken().isEmpty()) {
            Predicate predicate = cb.like(root.get("content"), "%" + searchCriteriaDto.searchToken() + "%");
            query.where(predicate);
        }
    }

    private void applySortingCriteria(SortingCriteriaDto sortingCriteriaDto,
                                      CriteriaBuilder cb,
                                      CriteriaQuery<Post> query,
                                      Root<Post> root) {

        if (sortingCriteriaDto == null) return;

        if (!sortingCriteriaDto.column().isEmpty()) {
            switch (sortingCriteriaDto.direction()) {
                case ASC -> query.orderBy(cb.asc(root.get(sortingCriteriaDto.column())));
                case DESC -> query.orderBy(cb.desc(root.get(sortingCriteriaDto.column())));
                default -> throw new IllegalStateException("Invalid direction: " + sortingCriteriaDto.direction());
            }
        }
    }

    private void applyPagingCriteria(PagingCriteriaDto pagingCriteriaDto,
                                      TypedQuery<Post> query) {

        if (pagingCriteriaDto == null) return;

        query.setFirstResult(pagingCriteriaDto.pageIndex());
        query.setMaxResults(pagingCriteriaDto.pageItemsCount());
    }

}
