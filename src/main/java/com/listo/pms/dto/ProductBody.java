package com.listo.pms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductBody {

    private String name;
    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}