package com.FOS.Foody.Repository;

import com.FOS.Foody.model.orderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<orderItem,Long> {
}
