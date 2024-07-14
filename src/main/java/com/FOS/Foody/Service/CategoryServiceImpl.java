package com.FOS.Foody.Service;

import com.FOS.Foody.Repository.CategoryRepository;
import com.FOS.Foody.model.Category;
import com.FOS.Foody.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    public RestaurantService restaurantService;

    @Autowired
    public CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantByUserId(userId);
        Category category=new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long Id) throws Exception {
        Optional<Category> optionalCategory=categoryRepository.findById(Id);
        if(optionalCategory.isEmpty()){
            throw new Exception("Category do not exist");
        }
        return optionalCategory.get();
    }
}
