package com.example.sentrycinterview.repository;

import com.example.sentrycinterview.models.SellerInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class SellerInfoSpecification {

    public static Specification<SellerInfo> filterSellerInfos(String sellerInfoName,
        List<String> marketPlaceIds) {
        // Dynamically build the query for filtering sellerinfo by name and marketplace ids
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (sellerInfoName != null) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")),
                    sellerInfoName.toLowerCase()));
            }

            if (marketPlaceIds != null && !marketPlaceIds.isEmpty()) {
                for (String id : marketPlaceIds) {
                    System.out.println("marketplace id: " + id);
                }
                predicates.add(root.get("marketplace").get("id").in(marketPlaceIds));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
