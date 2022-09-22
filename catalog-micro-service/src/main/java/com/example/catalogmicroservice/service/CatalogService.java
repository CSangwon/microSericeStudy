package com.example.catalogmicroservice.service;

import com.example.catalogmicroservice.entity.CatalogEntity;

public interface CatalogService {

    Iterable<CatalogEntity> getAllCatalogs();
}
