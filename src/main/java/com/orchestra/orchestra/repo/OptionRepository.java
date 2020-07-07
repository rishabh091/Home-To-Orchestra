package com.orchestra.orchestra.repo;

import com.orchestra.orchestra.modals.Singer;
import com.orchestra.orchestra.modals.SingingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<SingingOption, Long> {
    List<SingingOption> findAllBySinger(Singer singer);
    void deleteAllBySinger(Singer singer);
}
