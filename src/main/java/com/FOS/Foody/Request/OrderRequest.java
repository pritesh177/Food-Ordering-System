package com.FOS.Foody.Request;

import com.FOS.Foody.model.Addresses;
import lombok.Data;

@Data

public class OrderRequest {
    private Long restaurantId;
    private Addresses DeliveryAddress;
}
