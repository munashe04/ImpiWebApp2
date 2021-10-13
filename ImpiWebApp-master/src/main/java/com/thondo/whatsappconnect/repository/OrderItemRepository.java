package com.thondo.whatsappconnect.repository;

import com.thondo.whatsappconnect.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    Optional<OrderItem> findByName(String name);
    Optional<OrderItem> findByStatus(String status);
}
