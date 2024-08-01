package com.FOS.Foody.Service;

import com.FOS.Foody.Repository.IngredientCategoryRespository;
import com.FOS.Foody.Repository.IngredientItemRepository;
import com.FOS.Foody.model.IngredientCategory;
import com.FOS.Foody.model.IngredientItem;
import com.FOS.Foody.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRespository ingredientCategoryRespository;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long RestaurantId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(RestaurantId);

        IngredientCategory category=new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);

        return ingredientCategoryRespository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt=ingredientCategoryRespository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredient Category Not Found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRespository.findRestaurantById(id);
    }

    @Override
    public IngredientItem createIngredientItem(Long RestaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(RestaurantId);
        IngredientCategory category=findIngredientCategoryById(categoryId);

        IngredientItem item =new IngredientItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientItem ingredient=ingredientItemRepository.save(item);
        category.getIngredients().add(ingredient);
        return ingredient;
    }

    @Override
    public List<IngredientItem> findRestaurantsIngredients(Long Restaurantid) {
        return ingredientItemRepository.findRestaurantById(Restaurantid);
    }

    @Override
    public IngredientItem updateStock(Long id) throws Exception {
        Optional<IngredientItem> optionalIngredientItem=ingredientItemRepository.findById(id);
        if(optionalIngredientItem.isEmpty()){
            throw new Exception("Ingredient not Found");
        }

        IngredientItem ingredientItem=optionalIngredientItem.get();
        ingredientItem.setInStoke(ingredientItem.isInStoke());
        return ingredientItemRepository.save(ingredientItem);
    }
}
