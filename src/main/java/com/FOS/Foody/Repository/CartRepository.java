package com.FOS.Foody.Repository;

import com.FOS.Foody.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
