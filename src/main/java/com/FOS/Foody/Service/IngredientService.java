package com.FOS.Foody.Service;

import com.FOS.Foody.model.IngredientCategory;
import com.FOS.Foody.model.IngredientItem;

import java.util.List;

public interface IngredientService {
    public IngredientCategory createIngredientCategory(String name, Long RestaurantId)throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id)throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id)throws Exception;

    public IngredientItem createIngredientItem(Long RestaurantId, String ingredientName, Long categoryId) throws Exception;

    public List<IngredientItem> findRestaurantsIngredients(Long Restaurantid);

    public IngredientItem updateStock(Long id)throws Exception;
}
