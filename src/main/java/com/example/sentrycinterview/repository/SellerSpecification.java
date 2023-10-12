package com.example.sentrycinterview.repository;

import com.example.sentrycinterview.models.Seller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class SellerSpecification {

    public static Specification<Seller> filterSellers(UUID sellerInfoId, List<String> producerIds) {
        // Dynamically build the query for sellerInfoId and producerIds
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (sellerInfoId != null) {
                predicates.add(root.get("sellerInfo").get("id").in(sellerInfoId));
            }

            if (producerIds != null && !producerIds.isEmpty()) {
                predicates.add(root.get("producer").get("name").in(producerIds));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}