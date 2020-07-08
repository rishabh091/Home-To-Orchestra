package com.orchestra.orchestra.repo;

import com.orchestra.orchestra.modals.Order;
import com.orchestra.orchestra.modals.Order_Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_SingerRepository extends JpaRepository<Order_Singer, Long> {
    List<Order_Singer> findAllByOrder(Order order);
    void deleteAllByOrder(Order order);
}
