package com.vti.specification;

import com.vti.entity.Dish;
import com.vti.form.DishFilterForm;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DishSpecification {

    public static Specification<Dish> buildWhere(DishFilterForm filterForm) {
        return (root, query, criteriaBuilder) -> {
            if (filterForm == null) {
                return criteriaBuilder.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(filterForm.getSearch())) {
                String searchLike = "%" + filterForm.getSearch().trim().toLowerCase() + "%";
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchLike));
            }

            if (filterForm.getCategoryId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"), filterForm.getCategoryId()));
            }

            if (filterForm.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filterForm.getStatus()));
            }

            if (filterForm.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filterForm.getMinPrice()));
            }

            if (filterForm.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), filterForm.getMaxPrice()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
