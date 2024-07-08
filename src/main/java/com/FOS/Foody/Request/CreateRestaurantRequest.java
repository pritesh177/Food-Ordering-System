package com.FOS.Foody.Request;

import com.FOS.Foody.model.Addresses;
import com.FOS.Foody.model.ContactInformation;
import lombok.Data;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String CuisineType;
    private Addresses address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;

}
