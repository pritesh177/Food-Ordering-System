package com.FOS.Foody.Repository;

import com.FOS.Foody.model.IngredientItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientItem,Long> {
    List<IngredientItem> findRestaurantById(Long id);
}
