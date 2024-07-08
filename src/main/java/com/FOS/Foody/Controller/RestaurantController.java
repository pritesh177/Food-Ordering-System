package com.FOS.Foody.Controller;

import com.FOS.Foody.Dto.RestaurantDto;
import com.FOS.Foody.Request.CreateRestaurantRequest;
import com.FOS.Foody.Service.RestaurantService;
import com.FOS.Foody.Service.UserService;
import com.FOS.Foody.model.Restaurant;
import com.FOS.Foody.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String Keyword
    ) throws Exception {
        User user=userService.findByJwtToken(jwt);

        List<Restaurant> restaurant=restaurantService.searchRestaurants(Keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user=userService.findByJwtToken(jwt);

        List<Restaurant> restaurant=restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user=userService.findByJwtToken(jwt);

        Restaurant restaurant=restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-to-favorite")
    public ResponseEntity<RestaurantDto> addToFavorite(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        User user=userService.findByJwtToken(jwt);

        RestaurantDto restaurant=restaurantService.addToFavorites(id,user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }
}
