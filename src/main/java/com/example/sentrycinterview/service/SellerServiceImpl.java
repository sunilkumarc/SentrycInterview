package com.example.sentrycinterview.service;

import com.example.sentrycinterview.dto.FilterRequest;
import com.example.sentrycinterview.dto.MetaDTO;
import com.example.sentrycinterview.dto.ProducerSellerStateDTO;
import com.example.sentrycinterview.dto.SellerDTO;
import com.example.sentrycinterview.dto.SellerResponseDTO;
import com.example.sentrycinterview.enums.SellerSortBy;
import com.example.sentrycinterview.models.Seller;
import com.example.sentrycinterview.models.SellerInfo;
import com.example.sentrycinterview.repository.SellerInfoRepository;
import com.example.sentrycinterview.repository.SellerInfoSpecification;
import com.example.sentrycinterview.repository.SellerRepository;
import com.example.sentrycinterview.repository.SellerSpecification;
import com.example.sentrycinterview.utils.CommonUtils;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final SellerInfoRepository sellerInfoRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository,
        SellerInfoRepository sellerInfoRepository) {
        this.sellerRepository = sellerRepository;
        this.sellerInfoRepository = sellerInfoRepository;
    }

    @Override
    public SellerResponseDTO getSeller(FilterRequest filterRequest) {
        String sortBy =
            filterRequest.getSortDirection() != null ? filterRequest.getSortDirection() : "";
        SellerSortBy sellerSortBy = CommonUtils.getSortFieldAndDirection(sortBy);

        // Logic for pagination
        PageRequest pageRequest = PageRequest.of(filterRequest.getPage() - 1,
            filterRequest.getSize(),
            Sort.by(sellerSortBy.getDirection(), sellerSortBy.getFieldName()));

        // SellerInfoSpecification provides a way for dynamic filtering
        // In the future we can add more filters by just adding more filters
        // inside filterSellerInfos method
        Page<SellerInfo> sellerInfos = sellerInfoRepository.findAll(
            SellerInfoSpecification.filterSellerInfos(filterRequest.getSellerName(),
                filterRequest.getMarketplaceIds()),
            pageRequest
        );

        List<SellerDTO> sellerDTOs;
        boolean sellerInfosEmpty = sellerInfos != null ? sellerInfos.getTotalElements() == 0 : true;
        if (!sellerInfosEmpty) {
            sellerDTOs = sellerInfos.stream()
                .map(sellerInfo -> convertToSellerDTO(sellerInfo, filterRequest.getProducerIds()))
                .collect(Collectors.toList());
        } else {
            sellerDTOs = Collections.emptyList();
        }

        MetaDTO meta = new MetaDTO(
            !sellerInfosEmpty ? sellerInfos.getNumber() + 1 : 1,
            !sellerInfosEmpty ? sellerInfos.getSize() : 0,
            !sellerInfosEmpty ? (int) sellerInfos.getTotalElements() : 0
        );

        return new SellerResponseDTO(meta, sellerDTOs);
    }

    private SellerDTO convertToSellerDTO(SellerInfo sellerInfo, List<String> producerIds) {
        SellerDTO sellerDTO = new SellerDTO(sellerInfo.getName(), sellerInfo.getExternalId(),
            sellerInfo.getMarketplace().getId());
        // Fetch all the sellers for the corresponding seller info
        List<Seller> sellers = sellerRepository.findAll(
            SellerSpecification.filterSellers(sellerInfo.getId(), producerIds)
        );

        // Prepare ProducerSellerStateDTO object for each sellers
        List<ProducerSellerStateDTO> producerSellerStateDTOs = sellers.stream()
            .map(CommonUtils::convertToProducerSellerStateDTO)
            .collect(Collectors.toList());
        sellerDTO.setProducerSellerStates(producerSellerStateDTOs);
        return sellerDTO;
    }
}