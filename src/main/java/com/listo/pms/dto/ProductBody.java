package com.listo.pms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductBody {

    private String name;
    private int categoryId;
}