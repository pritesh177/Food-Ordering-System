package com.FOS.Foody.Request;

import lombok.Data;

import javax.swing.*;

@Data
public class IngredientRequest {
    private String name;
    private Long RestaurantId;
    private Long CategoryId;
}
