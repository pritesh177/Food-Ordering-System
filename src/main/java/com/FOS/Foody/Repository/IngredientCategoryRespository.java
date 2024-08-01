package com.FOS.Foody.Repository;

import com.FOS.Foody.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRespository extends JpaRepository<IngredientCategory,Long> {

    List<IngredientCategory> findRestaurantById(Long id);
}
