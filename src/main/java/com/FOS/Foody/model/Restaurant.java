package com.FOS.Foody.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @OneToOne
        private User owner;

        private String name;

        private String Description;
        private String cuisineType;

        @OneToOne
        private Addresses address;
        @Embedded
        private ContactInformation contactInformation;

        private String OpeningHours;

        @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
        private List<order> orders=new ArrayList<>();

        @ElementCollection
        @Column(length = 1000)
        private List<String> images;

        private LocalDateTime registrationDate;
        private boolean open;

        @JsonIgnore
        @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
        private List<Food> foods=new ArrayList<>();

    }
