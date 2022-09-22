package com.example.catalogmicroservice.controller;

import com.example.catalogmicroservice.dto.CatalogDto;
import com.example.catalogmicroservice.entity.CatalogEntity;
import com.example.catalogmicroservice.service.CatalogService;
import com.example.catalogmicroservice.vo.Greeting;
import com.example.catalogmicroservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-micro-service")
@RequiredArgsConstructor
public class CatalogController {

    private final Greeting greeting;

    private final CatalogService catalogService;

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in User Service on Port " + greeting.getServerPort();
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getUsers(){
        Iterable<CatalogEntity> userList = catalogService.getAllCatalogs();
        List<ResponseCatalog> result = new ArrayList<>();

        ResponseCatalog responseCatalog = new ResponseCatalog();
        userList.forEach(v -> {
            result.add(responseCatalog.toResponse(v));
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
