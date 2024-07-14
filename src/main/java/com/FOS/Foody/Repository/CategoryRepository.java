package com.FOS.Foody.Repository;

import com.FOS.Foody.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public List<Category>findByRestaurantId(Long id);
}
