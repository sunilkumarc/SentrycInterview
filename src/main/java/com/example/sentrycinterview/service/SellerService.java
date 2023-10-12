package com.example.sentrycinterview.service;

import com.example.sentrycinterview.dto.FilterRequest;
import com.example.sentrycinterview.dto.SellerResponseDTO;

public interface SellerService {

    SellerResponseDTO getSeller(FilterRequest filterRequest);
}