package com.FOS.Foody.Request;

import lombok.Data;

@Data
public class UpdateClassItemRequest {
    private Long cartItemId;
    private int quantity;
}
