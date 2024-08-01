package com.FOS.Foody.Controller;

import com.FOS.Foody.Request.IngredientCategoryRequest;
import com.FOS.Foody.Request.IngredientRequest;
import com.FOS.Foody.Service.IngredientService;
import com.FOS.Foody.model.IngredientCategory;
import com.FOS.Foody.model.IngredientItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception{
        IngredientCategory item=ingredientService.createIngredientCategory(req.getName(),req.getRestarantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientItem> CreateIngredientItem(
            @RequestBody IngredientRequest req
    ) throws Exception{
        IngredientItem item=ingredientService.createIngredientItem(req.getRestaurantId(),req.getName(),req.getCategoryId());
        return new ResponseEntity<>(item,HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientItem> updateIngredientStock(
            @PathVariable Long id
    ) throws Exception{
        IngredientItem item=ingredientService.updateStock(id);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredients(
            @PathVariable Long id
    ) throws Exception{
        List<IngredientItem> items=ingredientService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientsCategory(
            @PathVariable Long id
    ) throws Exception{
        List<IngredientCategory> items=ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items,HttpStatus.OK);
    }
}
