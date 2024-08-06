package com.FOS.Foody.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class orderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    private Food food;

    private int quantity;
    private Long totalPrice;
    private List<String> ingredients;
}
