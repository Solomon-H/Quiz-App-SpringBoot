package com.demo.quizApp.model;

import java.util.List;
import lombok.Data;

@Data
public class Quiz {
    private List<Question> questions;
    private List<Response> responses;

}