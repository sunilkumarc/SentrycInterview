package com.example.sentrycinterview.repository;

import com.example.sentrycinterview.models.Seller;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID>,
    JpaSpecificationExecutor<Seller> {

    Optional<Seller> findById(UUID id);
}
