package com.FOS.Foody.Service;

import com.FOS.Foody.Request.OrderRequest;
import com.FOS.Foody.model.User;
import com.FOS.Foody.model.order;

import java.util.List;

public interface OrderService {
    public order createOrder(OrderRequest req, User user)throws Exception;

    public order updateOrder(Long orderId,String orderStatus) throws Exception;

    public void cancelOrder(Long orderId)throws Exception;

    public List<order> getUsersOrders(Long userId) throws Exception;

    public List<order> getAllRestaurantsOrder(Long restaurantId,String orderStatus) throws Exception;

    public order findOrderById(Long orderId) throws Exception;
}
