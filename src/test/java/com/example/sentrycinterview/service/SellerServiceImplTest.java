package com.example.sentrycinterview.service;

import com.example.sentrycinterview.dto.FilterRequest;
import com.example.sentrycinterview.dto.SellerResponseDTO;
import com.example.sentrycinterview.enums.SellerSortBy;
import com.example.sentrycinterview.models.SellerInfo;
import com.example.sentrycinterview.repository.SellerInfoRepository;
import com.example.sentrycinterview.repository.SellerInfoSpecification;
import com.example.sentrycinterview.repository.SellerRepository;
import com.example.sentrycinterview.utils.CommonUtils;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        sellerInfoRepository = mock(SellerInfoRepository.class);
    }

    @Test
    void testGetSeller() {
        FilterRequest filterRequest = new FilterRequest("sellerName", Collections.singletonList("prodId"),
            Collections.singletonList("marketId"), "NAME_ASC", 1, 10);

        List<SellerInfo> sellerInfoList = new ArrayList<>();
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(UUID.randomUUID());
        sellerInfo.setName("Example Seller");
        sellerInfo.setExternalId("123456");
        sellerInfoList.add(sellerInfo);

        Page<SellerInfo> sellerInfoPage = new PageImpl<>(sellerInfoList);

        SellerSortBy sellerSortBy = CommonUtils.getSortFieldAndDirection("NAME_ASC");
        PageRequest pageRequest = PageRequest.of(filterRequest.getPage() - 1,
            filterRequest.getSize(),
            Sort.by(sellerSortBy.getDirection(), sellerSortBy.getFieldName()));

        // Mock repository methods
        Specification<SellerInfo> spec = SellerInfoSpecification.filterSellerInfos(
            filterRequest.getSellerName(), filterRequest.getMarketplaceIds());
        when(sellerInfoRepository.findAll(eq(spec), eq(pageRequest))).thenReturn(sellerInfoPage);

        SellerResponseDTO responseDTO = sellerService.getSeller(filterRequest);
        assertEquals(0, responseDTO.getData().size());
        assertEquals(0, responseDTO.getMeta().getSize());
    }
}
