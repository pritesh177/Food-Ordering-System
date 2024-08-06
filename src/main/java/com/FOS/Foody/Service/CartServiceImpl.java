package com.FOS.Foody.Service;

import com.FOS.Foody.Repository.CartItemRepository;
import com.FOS.Foody.Repository.CartRepository;
import com.FOS.Foody.Repository.FoodRepository;
import com.FOS.Foody.Request.AddCartItemRequest;
import com.FOS.Foody.model.Cart;
import com.FOS.Foody.model.Food;
import com.FOS.Foody.model.User;
import com.FOS.Foody.model.cartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private FoodService foodService;

    @Override
    public cartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user=userService.findByJwtToken(jwt);
        Food food=foodService.findFoodById(req.getFoodId());
        Cart cart=cartRepository.findByCustomerId(user.getId());

        for(cartItem cartItem:cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int newQuantity=cartItem.getQuantity()+ req.getQuantity();
                return updatecartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        cartItem newcartitem=new cartItem();
        newcartitem.setFood(food);
        newcartitem.setCart(cart);
        newcartitem.setQuantity(req.getQuantity());
        newcartitem.setIngredients(req.getIngredients());
        newcartitem.setTotalPrice(req.getQuantity()* food.getPrice());
        cartItem saveCartItem=cartItemRepository.save(newcartitem);
        cart.getItem().add(saveCartItem);

        return saveCartItem;
    }

    @Override
    public cartItem updatecartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<cartItem> cartItemOptional=cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }

        cartItem item=cartItemOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice()*quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user=userService.findByJwtToken(jwt);
        Cart cart=cartRepository.findByCustomerId(user.getId());

        Optional<cartItem> cartItemOptional=cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }

        cartItem item=cartItemOptional.get();
        cart.getItem().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total=0L;
        for(cartItem cartitem:cart.getItem()){
            total+=cartitem.getFood().getPrice()* cartitem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long Id) throws Exception {
        Optional<Cart> optionalCart=cartRepository.findById(Id);
        if(optionalCart.isEmpty()){
            throw new Exception("cart not found with id "+Id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        //User user=userService.findByJwtToken(jwt);
        Cart cart= cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart cleanCart(Long userId) throws Exception {
        //User user=userService.findByJwtToken(jwt);
        Cart cart=findCartByUserId(userId);
        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}
