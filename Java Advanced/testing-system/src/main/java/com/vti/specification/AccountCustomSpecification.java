package com.vti.specification;

import com.vti.entity.Account;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


@AllArgsConstructor
public class AccountCustomSpecification implements Specification<Account> {

    private String field;

    private Object value;

    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        if ("username".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("username"), "%" + value + "%");
            //  username  like   '%abc%'
        }

        if ("fullName".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("fullName"), "%" + value + "%");
            //  fullName  like   '%abc%'
        }

        if ("email".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("email"), "%" + value + "%");
            //  email  like   '%abc%'
        }

        if ("departmentId".equalsIgnoreCase(field)) {
            return criteriaBuilder.equal(root.get("department").get("id"), value);
            //  departmentId  =   value
        }

        if ("positionId".equalsIgnoreCase(field)) {
            return criteriaBuilder.equal(root.get("position").get("id"), value);
            //  positionId  =   value
        }
        return null;
    }
}