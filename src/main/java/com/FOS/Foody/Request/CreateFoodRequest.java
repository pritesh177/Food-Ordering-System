package com.FOS.Foody.Request;

import com.FOS.Foody.model.Category;
import com.FOS.Foody.model.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;

    private List<IngredientItem> ingredients;
}
