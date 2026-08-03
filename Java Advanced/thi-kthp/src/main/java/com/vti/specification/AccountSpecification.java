package com.vti.specification;

import com.vti.entity.Account;
import com.vti.form.AccountSearchForm;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AccountSpecification {

    public static Specification<Account> buildWhere(AccountSearchForm searchForm) {
        return (root, query, criteriaBuilder) -> {
            if (searchForm == null) {
                return criteriaBuilder.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(searchForm.getSearch())) {
                String searchLike = "%" + searchForm.getSearch().trim().toLowerCase() + "%";
                Predicate usernameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), searchLike);
                Predicate fullNameMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), searchLike);
                Predicate emailMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), searchLike);
                predicates.add(criteriaBuilder.or(usernameMatch, fullNameMatch, emailMatch));
            }

            if (searchForm.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), searchForm.getRole()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
