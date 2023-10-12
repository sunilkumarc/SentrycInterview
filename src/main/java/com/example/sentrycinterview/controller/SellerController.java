package com.example.sentrycinterview.controller;

import com.example.sentrycinterview.dto.FilterRequest;
import com.example.sentrycinterview.dto.SellerResponseDTO;
import com.example.sentrycinterview.exceptions.BadRequestException;
import com.example.sentrycinterview.service.SellerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sellers")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @GetMapping("/filter")
    public ResponseEntity<SellerResponseDTO> getSellers(
        @RequestParam(required = false) String sellerName,
        @RequestParam(required = false) List<String> producerIds,
        @RequestParam(required = false) List<String> marketplaceIds,
        @RequestParam Integer page,
        @RequestParam Integer size,
        @RequestParam(required = false) String sortDirection) {

        // Add basic validations in the controller layer
        if (page == null || page < 1) {
            throw new BadRequestException(
                "Invalid page number. Page number must be greater than or equal to 1.");
        }
        if (size == null || (size < 1 || size > 100)) {
            throw new BadRequestException(
                "Invalid page size. Page size must be between 1 and 100.");
        }

        FilterRequest filterRequest = new FilterRequest(sellerName, producerIds, marketplaceIds,
            sortDirection, page, size);
        return ResponseEntity.ok(sellerService.getSeller(filterRequest));
    }
}
