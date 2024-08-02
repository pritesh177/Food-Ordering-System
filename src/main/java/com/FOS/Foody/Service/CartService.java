package com.FOS.Foody.Service;

import com.FOS.Foody.Request.AddCartItemRequest;
import com.FOS.Foody.model.Cart;
import com.FOS.Foody.model.cartItem;

public interface CartService {

    public cartItem addItemToCart (AddCartItemRequest req,String jwt) throws Exception;

    public cartItem updatecartItemQuantity(Long cartItemId,int quantity)throws Exception;

    public Cart removeItemFromCart(Long cartItemId,String jwt)throws Exception;

    public Long calculateCartTotals(Cart cart)throws Exception;

    public Cart findCartById(Long Id)throws Exception;

    public Cart findCartByUserId(String jwt)throws Exception;

    public Cart cleanCart(String jwt) throws Exception;
}
