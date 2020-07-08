package com.orchestra.orchestra.repo;

import com.orchestra.orchestra.modals.Vocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocalRepository extends JpaRepository<Vocal, Long> {
}
