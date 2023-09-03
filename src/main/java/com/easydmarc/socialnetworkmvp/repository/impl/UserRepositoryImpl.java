package com.easydmarc.socialnetworkmvp.repository.impl;

import com.easydmarc.socialnetworkmvp.dto.*;
import com.easydmarc.socialnetworkmvp.model.User;
import com.easydmarc.socialnetworkmvp.repository.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers(CriteriaDto criteriaDto) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.multiselect(root.get("id"), root.get("firstName"), root.get("lastName"), root.get("email"), root.get("avatarPath"));
        applySearchCriteria(criteriaDto.searchCriteria(), cb, query, root);
        applySortingCriteria(criteriaDto.sortingCriteria(), cb, query, root);
        TypedQuery<User> createdQuery = entityManager.createQuery(query);
        applyPagingCriteria(criteriaDto.pagingCriteria(), createdQuery);

        return createdQuery.getResultList();
    }

    private void applySearchCriteria(SearchCriteriaDto searchCriteriaDto,
                                     CriteriaBuilder cb,
                                     CriteriaQuery<User> query,
                                     Root<User> root) {

        if (searchCriteriaDto == null) return;

        if (searchCriteriaDto.searchToken() != null && !searchCriteriaDto.searchToken().isEmpty()) {
            Predicate[] predicates = new Predicate[3];
            predicates[0] = cb.like(root.get("firstName"), "%" + searchCriteriaDto.searchToken() + "%");
            predicates[1] = cb.like(root.get("lastName"), "%" + searchCriteriaDto.searchToken() + "%");
            predicates[2] = cb.like(root.get("email"), "%" + searchCriteriaDto.searchToken() + "%");
            query.where(predicates);
        }
    }

    private void applySortingCriteria(SortingCriteriaDto sortingCriteriaDto,
                                      CriteriaBuilder cb,
                                      CriteriaQuery<User> query,
                                      Root<User> root) {

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
                                      TypedQuery<User> query) {

        if (pagingCriteriaDto == null) return;

        query.setFirstResult(pagingCriteriaDto.pageIndex());
        query.setMaxResults(pagingCriteriaDto.pageItemsCount());
    }

}
