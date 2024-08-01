package com.FOS.Foody.Request;

import lombok.Data;

@Data
public class IngredientCategoryRequest {
    private String name;
    private Long restarantId;
}
