package com.FOS.Foody.Service;

import com.FOS.Foody.Repository.AddressRepository;
import com.FOS.Foody.Repository.OrderItemRepository;
import com.FOS.Foody.Repository.OrderRepository;
import com.FOS.Foody.Repository.UserRepository;
import com.FOS.Foody.Request.OrderRequest;
import com.FOS.Foody.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private AddressRepository addressRepository;
    private RestaurantService restaurantService;
    private UserRepository userRepository;
    private CartService cartService;


    @Override
    public order createOrder(OrderRequest req, User user) throws Exception {
        Addresses shippingaddress=req.getDeliveryAddress();

        Addresses savedAddress=addressRepository.save(shippingaddress);

        if(!user.getAddress().contains(savedAddress)){
            user.getAddress().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant=restaurantService.findRestaurantById(req.getRestaurantId());

        order CreatedOrder=new order();
        CreatedOrder.setCustomer(user);
        CreatedOrder.setCreatedAt(new Date());
        CreatedOrder.setOrderStatus("PENDING");
        CreatedOrder.setDeliveryAddress(savedAddress);
        CreatedOrder.setRestaurant(restaurant);

        Cart cart=cartService.findCartByUserId(user.getId());
        List<orderItem> orderItems=new ArrayList<>();

        for(cartItem cartItems:cart.getItem()){
            orderItem OrderItem=new orderItem();
            OrderItem.setFood(cartItems.getFood());
            OrderItem.setIngredients(cartItems.getIngredients());
            OrderItem.setQuantity(cartItems.getQuantity());
            OrderItem.setTotalPrice(cartItems.getTotalPrice());

            orderItem saveOrderItem=orderItemRepository.save(OrderItem);
            orderItems.add(saveOrderItem);
        }

        Long totalprice=cartService.calculateCartTotals(cart);
        CreatedOrder.setItems(orderItems);
        CreatedOrder.setTotalPrice(totalprice);

        order savedOrder=orderRepository.save(CreatedOrder);
        restaurant.getOrders().add(savedOrder);


        return CreatedOrder;
    }

    @Override
    public order updateOrder(Long orderId, String orderStatus) throws Exception {
        order Order=findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY")||
                orderStatus.equals("COMPLETED")||
                orderStatus.equals("DELIVERED") ||
                orderStatus.equals("PENDING")){
            Order.setOrderStatus(orderStatus);
            return orderRepository.save(Order);
        }
        throw new Exception("Please Select a Valid Order Status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        order Order=findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<order> getUsersOrders(Long userId) {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<order> getAllRestaurantsOrder(Long restaurantId, String orderStatus) {
        List<order> orders=orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus!=null){
            orders=orders.stream().filter(order -> order.getOrderStatus()
                    .equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public order findOrderById(Long orderId) throws Exception {
        Optional<order> optionalOrder=orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("Order not found");
        }
        return optionalOrder.get();
    }
}
