package com.demo.quizApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String answer;
    private String category;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

}