package com.example.sentrycinterview.utils;

import com.example.sentrycinterview.dto.ProducerSellerStateDTO;
import com.example.sentrycinterview.enums.SellerSortBy;
import com.example.sentrycinterview.models.Seller;

public class CommonUtils {

    public static SellerSortBy getSortFieldAndDirection(String input) {
        try {
            return SellerSortBy.valueOf(input);
        } catch (IllegalArgumentException e) {
            // Default sort field and direction
            return SellerSortBy.NAME_ASC;
        }
    }

    public static ProducerSellerStateDTO convertToProducerSellerStateDTO(Seller seller) {
        ProducerSellerStateDTO producerSellerStateDTOs = new ProducerSellerStateDTO();
        producerSellerStateDTOs.setProducerId(String.valueOf(seller.getProducer().getId()));
        producerSellerStateDTOs.setProducerName(seller.getProducer().getName());
        producerSellerStateDTOs.setSellerId(String.valueOf(seller.getId()));
        producerSellerStateDTOs.setSellerState(seller.getState());
        return producerSellerStateDTOs;
    }
}
