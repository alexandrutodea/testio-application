package me.alextodea.testioapplication.repository;

import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findBySubmitter(AppUser submitter);
}
