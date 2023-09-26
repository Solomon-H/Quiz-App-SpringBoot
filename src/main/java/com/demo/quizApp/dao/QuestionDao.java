package com.demo.quizApp.dao;

import com.demo.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    @Query(value = "SELECT * FROM Question ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestions(@Param("numQ") int numQ);
}
