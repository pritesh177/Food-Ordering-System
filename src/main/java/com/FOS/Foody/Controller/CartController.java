package com.FOS.Foody.Controller;

import com.FOS.Foody.Request.AddCartItemRequest;
import com.FOS.Foody.Request.UpdateClassItemRequest;
import com.FOS.Foody.Service.CartService;
import com.FOS.Foody.model.Cart;
import com.FOS.Foody.model.cartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @PutMapping("/cart/add")
    public ResponseEntity<cartItem> addItemToCart(@RequestBody AddCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception{
        cartItem cartitem=cartService.addItemToCart(req,jwt);
        return new ResponseEntity<>(cartitem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<cartItem> updateCartItemQuantity(@RequestBody UpdateClassItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception{
        cartItem cartitem=cartService.updatecartItemQuantity(req.getCartItemId(), req.getQuantity());
        return new ResponseEntity<>(cartitem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                                           @RequestHeader("Authorization") String jwt) throws Exception{
        Cart cart=cartService.removeItemFromCart(id,jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception{
    Cart cart=cartService.cleanCart(jwt);
    return new ResponseEntity<>(cart,HttpStatus.OK);
    }
}
