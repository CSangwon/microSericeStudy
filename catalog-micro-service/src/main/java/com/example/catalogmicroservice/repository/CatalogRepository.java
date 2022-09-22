package com.example.catalogmicroservice.repository;

import com.example.catalogmicroservice.entity.CatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CatalogRepository extends JpaRepository<CatalogEntity, UUID> {
    Optional<CatalogEntity> findByProductId(String productId);
}
