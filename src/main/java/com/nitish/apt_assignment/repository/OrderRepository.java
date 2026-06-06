package com.nitish.apt_assignment.repository;

import com.nitish.apt_assignment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
