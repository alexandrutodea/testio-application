package me.alextodea.testioapplication.repository;

import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findExerciseByAuthor(AppUser appUser);
}
