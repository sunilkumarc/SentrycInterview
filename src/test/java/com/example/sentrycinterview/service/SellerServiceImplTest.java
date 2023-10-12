package com.example.sentrycinterview.service;

import com.example.sentrycinterview.dto.FilterRequest;
import com.example.sentrycinterview.dto.MetaDTO;
import com.example.sentrycinterview.dto.ProducerSellerStateDTO;
import com.example.sentrycinterview.dto.SellerDTO;
import com.example.sentrycinterview.dto.SellerResponseDTO;
import com.example.sentrycinterview.enums.SellerSortBy;
import com.example.sentrycinterview.models.Marketplace;
import com.example.sentrycinterview.models.Seller;
import com.example.sentrycinterview.models.SellerInfo;
import com.example.sentrycinterview.repository.SellerInfoRepository;
import com.example.sentrycinterview.repository.SellerInfoSpecification;
import com.example.sentrycinterview.repository.SellerRepository;
import com.example.sentrycinterview.repository.SellerSpecification;
import com.example.sentrycinterview.service.SellerServiceImpl;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SellerServiceImplTest {

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private SellerInfoRepository sellerInfoRepository;

    @InjectMocks
    private SellerServiceImpl sellerService;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
        sellerInfoRepository = mock(SellerInfoRepository.class);
    }

    @Test
    void testGetSeller() {
        FilterRequest filterRequest = new FilterRequest("sellerName", Collections.singletonList("prodId"),
            Collections.singletonList("marketId"), "asc", 1, 10);

        filterRequest.setPage(1);
        filterRequest.setSize(10);
        filterRequest.setSortDirection("asc");
        filterRequest.setSellerName("Example Seller");

        List<SellerInfo> sellerInfoList = new ArrayList<>();
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(UUID.randomUUID());
        sellerInfo.setName("Example Seller");
        sellerInfo.setExternalId("123456");
        sellerInfoList.add(sellerInfo);

        Page<SellerInfo> sellerInfoPage = new PageImpl<>(sellerInfoList);

        // Mock repository methods
        when(sellerInfoRepository.findAll(
            (Specification<SellerInfo>)any(SellerInfoSpecification.class), any(PageRequest.class)))
            .thenReturn(sellerInfoPage);

        SellerResponseDTO responseDTO = sellerService.getSeller(filterRequest);
        assertEquals(0, responseDTO.getData().size());
    }
}
