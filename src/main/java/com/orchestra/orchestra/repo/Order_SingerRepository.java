package com.orchestra.orchestra.repo;

import com.orchestra.orchestra.modals.Order_Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_SingerRepository extends JpaRepository<Order_Singer, Long> {
}
