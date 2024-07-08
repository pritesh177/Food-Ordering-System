package com.FOS.Foody.Service;

import com.FOS.Foody.Dto.RestaurantDto;
import com.FOS.Foody.Request.CreateRestaurantRequest;
import com.FOS.Foody.model.Restaurant;
import com.FOS.Foody.model.User;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest UpdateRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurants(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant findRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId,User user)throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;


}
