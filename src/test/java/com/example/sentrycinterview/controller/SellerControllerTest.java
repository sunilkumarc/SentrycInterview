package com.example.sentrycinterview.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.example.sentrycinterview.dto.FilterRequest;
import com.example.sentrycinterview.dto.SellerResponseDTO;
import com.example.sentrycinterview.exceptions.BadRequestException;
import com.example.sentrycinterview.service.SellerService;
import java.util.Collections;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class SellerControllerTest {

    @Mock
    private SellerService sellerService;

    @Autowired
    private SellerController sellerController;


    @BeforeEach
    void setUp() {
        sellerService = Mockito.mock(SellerService.class);
    }

    @Test
    void testGetSellersWithInvalidValidRequest() {
        BadRequestException exception = assertThrows(BadRequestException.class, () ->
            sellerController.getSellers("sellerName", Collections.singletonList("prodId"),
                Collections.singletonList("marketId"), 0, 10, "asc"));
        assertEquals("Invalid page number. Page number must be greater than or equal to 1.", exception.getMessage());

        exception = assertThrows(BadRequestException.class, () ->
            sellerController.getSellers("sellerName", Collections.singletonList("prodId"),
                Collections.singletonList("marketId"), 1, 200, "asc"));
        assertEquals("Invalid page size. Page size must be between 1 and 100.", exception.getMessage());
    }

    @Test
    void testGetSellersWithValidRequest() {
        // Arrange
//        FilterRequest filterRequest = new FilterRequest("sellerName", Collections.singletonList("prodId"),
//            Collections.singletonList("marketId"), "asc", 1, 10);
//
//        SellerResponseDTO expectedResponse = new SellerResponseDTO(null, null);
//        when(sellerService.getSeller(filterRequest)).thenReturn(expectedResponse);

        ResponseEntity<SellerResponseDTO> responseEntity = sellerController.getSellers("sellerName",
            Collections.singletonList("prodId"), Collections.singletonList("marketId"), 1, 10, "asc");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).getMeta().getPage());
    }
}