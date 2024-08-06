package com.FOS.Foody.Controller;

import com.FOS.Foody.Request.OrderRequest;
import com.FOS.Foody.Service.OrderService;
import com.FOS.Foody.Service.UserService;
import com.FOS.Foody.model.User;
import com.FOS.Foody.model.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class AdminOrderController {
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findByJwtToken(jwt);
        List<order> Orders=orderService.getAllRestaurantsOrder(id,order_status);
        return new ResponseEntity<>(Orders, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<order> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findByJwtToken(jwt);
        order Orders=orderService.updateOrder(id,order_status);
        return new ResponseEntity<>(Orders, HttpStatus.OK);
    }
}
