package com.example.catalogmicroservice.vo;

import com.example.catalogmicroservice.entity.CatalogEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {

    private String productId;

    private String productName;

    private Integer unitPrice;

    private Integer stock;

    private Date createAt;

    public ResponseCatalog toResponse(CatalogEntity catalogEntity) {
        return ResponseCatalog.builder()
                .productId(catalogEntity.getProductId())
                .productName(catalogEntity.getProductName())
                .unitPrice(catalogEntity.getUnitPrice())
                .stock(catalogEntity.getStock())
                .createAt(catalogEntity.getCreatedAt())
                .build();
    }
}
