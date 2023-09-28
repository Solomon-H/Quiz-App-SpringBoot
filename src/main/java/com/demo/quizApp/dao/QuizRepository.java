package com.demo.quizApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.quizApp.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}