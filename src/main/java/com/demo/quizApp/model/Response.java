package com.demo.quizApp.model;

import java.util.List;
import lombok.Data;

@Data
public class Response {
    private Long questionId;
    private String selectedAnswers;
    
}