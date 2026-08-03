package com.vti.specification;

import com.vti.entity.Order;
import com.vti.form.OrderFilterForm;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    public static Specification<Order> buildWhere(OrderFilterForm filterForm) {
        return (root, query, criteriaBuilder) -> {
            if (filterForm == null) {
                return criteriaBuilder.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            if (filterForm.getTableId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("diningTable").get("id"), filterForm.getTableId()));
            }

            if (filterForm.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filterForm.getStatus()));
            }

            if (StringUtils.isNotBlank(filterForm.getSearch())) {
                String searchLike = "%" + filterForm.getSearch().trim().toLowerCase() + "%";
                Predicate tableMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("diningTable").get("tableNumber")), searchLike);
                Predicate userMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("user").get("username")), searchLike);
                predicates.add(criteriaBuilder.or(tableMatch, userMatch));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
