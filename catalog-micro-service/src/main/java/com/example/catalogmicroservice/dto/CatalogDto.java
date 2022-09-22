package com.example.catalogmicroservice.dto;

import com.example.catalogmicroservice.entity.CatalogEntity;
import com.example.catalogmicroservice.vo.ResponseCatalog;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class CatalogDto implements Serializable {

    private String productId;

    private Integer qty;

    private Integer unitPrice;

    private Integer totalPrice;

    private UUID orderId;

    private UUID userId;

}
