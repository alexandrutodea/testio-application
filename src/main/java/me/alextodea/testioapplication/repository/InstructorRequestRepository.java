package me.alextodea.testioapplication.repository;

import me.alextodea.testioapplication.model.InstructorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRequestRepository extends JpaRepository<InstructorRequest, Long> {



}
