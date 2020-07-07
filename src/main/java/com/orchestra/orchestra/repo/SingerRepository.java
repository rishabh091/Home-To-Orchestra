package com.orchestra.orchestra.repo;

import com.orchestra.orchestra.modals.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {
    Optional<Singer> findByEmail(String email);
}
