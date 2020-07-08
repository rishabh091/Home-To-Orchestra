package com.orchestra.orchestra.repo;

import com.orchestra.orchestra.modals.Order;
import com.orchestra.orchestra.modals.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
