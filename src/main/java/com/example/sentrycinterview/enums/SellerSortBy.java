package com.example.sentrycinterview.enums;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public enum SellerSortBy {
    SELLER_INFO_EXTERNAL_ID_ASC("externalId", Direction.ASC),
    SELLER_INFO_EXTERNAL_ID_DESC("externalId", Direction.DESC),
    NAME_ASC("name", Direction.ASC),
    NAME_DESC("name", Direction.DESC),
    MARKETPLACE_ID_ASC("marketplaceId", Direction.ASC),
    MARKETPLACE_ID_DESC("marketplaceId", Direction.DESC),
    ;

    private String fieldName;
    private Sort.Direction direction;

    SellerSortBy(String fieldName, Sort.Direction direction) {
        this.fieldName = fieldName;
        this.direction = direction;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Sort.Direction getDirection() {
        return direction;
    }
}
