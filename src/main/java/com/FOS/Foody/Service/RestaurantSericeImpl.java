package com.FOS.Foody.Service;

import com.FOS.Foody.Dto.RestaurantDto;
import com.FOS.Foody.Repository.AddressRepository;
import com.FOS.Foody.Repository.RestaurantRepository;
import com.FOS.Foody.Repository.UserRepository;
import com.FOS.Foody.Request.CreateRestaurantRequest;
import com.FOS.Foody.model.Addresses;
import com.FOS.Foody.model.Restaurant;
import com.FOS.Foody.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class RestaurantSericeImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Addresses address=addressRepository.save(req.getAddress());

        Restaurant restaurant=new Restaurant();
        restaurant.setName(req.getName());
        restaurant.setAddress(address);
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest UpdateRestaurant) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);

        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(UpdateRestaurant.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(UpdateRestaurant.getDescription());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(UpdateRestaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants()
    {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt=restaurantRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Restaurant Not Found with Id "+id);
        }
        return opt.get();
    }

    @Override
    public Restaurant findRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant=restaurantRepository.findByOwnerId(userId);

        if(restaurant==null){
            throw new Exception("Restaurant Not Found with Id "+userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);
        RestaurantDto dto=new RestaurantDto();
        dto.setDescription((restaurant.getDescription()));
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        boolean isFavorite=false;
        List<RestaurantDto> favourites=user.getFavorites();
        for(RestaurantDto favorite:favourites){
            if(favorite.getId().equals(restaurantId)){
                isFavorite=true;
                break;
            }
        }

        if(isFavorite){
            favourites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        }else{
            favourites.add(dto);
        }


        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant=findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
