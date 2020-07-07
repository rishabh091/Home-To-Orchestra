package com.orchestra.orchestra.repo;

import com.orchestra.orchestra.modals.SingingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<SingingOption, Long> {
}
