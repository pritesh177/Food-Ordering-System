package com.FOS.Foody.Repository;

import com.FOS.Foody.model.cartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<cartItem,Long> {
}
