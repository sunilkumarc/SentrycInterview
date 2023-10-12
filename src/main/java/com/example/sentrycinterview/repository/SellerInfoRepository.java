package com.example.sentrycinterview.repository;

import com.example.sentrycinterview.models.SellerInfo;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo, UUID>,
    JpaSpecificationExecutor<SellerInfo> {

    List<SellerInfo> findByName(String name);
}