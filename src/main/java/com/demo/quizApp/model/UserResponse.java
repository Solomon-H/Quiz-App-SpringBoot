package com.demo.quizApp.model;

import lombok.Data;

@Data
public class UserResponse {
    private Long questionId;
    private String response;
}