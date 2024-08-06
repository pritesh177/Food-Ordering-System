package com.FOS.Foody.Controller;

import com.FOS.Foody.Request.AddCartItemRequest;
import com.FOS.Foody.Request.OrderRequest;
import com.FOS.Foody.Service.OrderService;
import com.FOS.Foody.Service.UserService;
import com.FOS.Foody.model.User;
import com.FOS.Foody.model.cartItem;
import com.FOS.Foody.model.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<order> getOrder(@RequestBody OrderRequest req,
                                          @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findByJwtToken(jwt);
        order Order=orderService.createOrder(req,user);
        return new ResponseEntity<>(Order, HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<order>> getOrderHistory(
                                          @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findByJwtToken(jwt);
        List<order> Orders=orderService.getUsersOrders(user.getId());
        return new ResponseEntity<>(Orders, HttpStatus.OK);
    }
}
