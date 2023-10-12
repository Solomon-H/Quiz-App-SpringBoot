package com.demo.quizApp.model;

import lombok.Data;

@Data
public class Response {
    private Long questionId;
    private String selectedAnswers;
    
}