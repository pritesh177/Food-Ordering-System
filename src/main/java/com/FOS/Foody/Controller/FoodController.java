package com.FOS.Foody.Controller;

import com.FOS.Foody.Request.CreateFoodRequest;
import com.FOS.Foody.Service.FoodService;
import com.FOS.Foody.Service.RestaurantService;
import com.FOS.Foody.Service.UserService;
import com.FOS.Foody.model.Food;
import com.FOS.Foody.model.Restaurant;
import com.FOS.Foody.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private FoodService foodService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findByJwtToken(jwt);
        List<Food> foods=foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
                                                        @RequestParam boolean seasonal,
                                                        @RequestParam boolean nonveg,
                                                        @RequestParam(required = false) String food_category,
                                                 @PathVariable Long restaurantId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findByJwtToken(jwt);
        List<Food> foods=foodService.getRestaurantsFood(restaurantId,vegetarian,seasonal,nonveg,food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
