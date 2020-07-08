package com.orchestra.orchestra.repo;

import com.orchestra.orchestra.modals.Vocal;
import com.orchestra.orchestra.modals.Vocal_Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Vocal_SingerRepository extends JpaRepository<Vocal_Singer, Long> {

    List<Vocal_Singer> findAllByVocal(Vocal vocal);
}
