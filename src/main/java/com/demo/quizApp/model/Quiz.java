package com.demo.quizApp.model;

import lombok.Data;

@Data
public class Quiz {

    private Long id;
    private String category;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    
}