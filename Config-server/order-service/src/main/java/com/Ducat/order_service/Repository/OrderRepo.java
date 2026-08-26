package com.Ducat.order_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ducat.order_service.Entity.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity,Long> {
    
}
