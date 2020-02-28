package com.psych.harishpsych.repository;

import com.psych.harishpsych.model.EllenAnswer;
import com.psych.harishpsych.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EllenAnswerRepository extends JpaRepository<EllenAnswer, Long> {
    @Query(value = "SELECT * FROM ellenAnswers limit 1", nativeQuery =  true)//todo
    EllenAnswer getRandomEllenAnswer(Question question);
}
