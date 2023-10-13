package com.example.sentrycinterview.repository;

import com.example.sentrycinterview.models.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SellerSpecificationTest {

    @InjectMocks
    private SellerSpecification sellerSpecification;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<Seller> criteriaQuery;

    @Mock
    private Root<Seller> root;

    @Mock
    private Path<Object> sellerInfoPath;

    @Mock
    private Path<Object> producerPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Mock behavior for root.get("sellerInfo") and root.get("producer")
        when(root.get("sellerInfo")).thenReturn(sellerInfoPath);
        when(root.get("producer")).thenReturn(producerPath);

        // Mock chained method calls using deep stubbing
        Path<Object> sellerInfoPath = mock(Path.class);
        Path<Object> sellerInfoIdPath = mock(Path.class);
        Path<Object> producerPath = mock(Path.class);

        when(root.get("sellerInfo")).thenReturn(sellerInfoPath);
        when(sellerInfoPath.get("id")).thenReturn(sellerInfoIdPath);
        when(root.get("producer")).thenReturn(producerPath);

        // Ensure proper typing for the paths
        when(sellerInfoIdPath.as(UUID.class)).thenReturn(mock(Path.class));
        when(producerPath.as(String.class)).thenReturn(mock(Path.class));
    }

    @Test
    void testFilterSellers() {
        // Arrange
        UUID sellerInfoId = UUID.randomUUID();
        List<String> producerIds = Arrays.asList("producer1", "producer2");

        Predicate sellerInfoPredicate = mock(Predicate.class);
        Predicate producerPredicate = mock(Predicate.class);

        when(criteriaBuilder.and(any(), any())).thenReturn(mock(Predicate.class));
        when(sellerInfoPath.in(any(UUID.class))).thenReturn(sellerInfoPredicate);
        when(producerPath.in(any(String.class))).thenReturn(producerPredicate);

        Specification<Seller> specification = SellerSpecification.filterSellers(sellerInfoId, producerIds);

        verifyNoInteractions(root);
        verifyNoInteractions(sellerInfoPath);
        verifyNoInteractions(producerPath);
    }
}
