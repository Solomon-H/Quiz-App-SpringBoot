package com.demo.quizApp.controller;


import com.demo.quizApp.model.Question;
import com.demo.quizApp.model.Response;
import com.demo.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/newQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

//    @PostMapping("/totalAnswer/{id}")
//    public ResponseEntity<Integer> getTotalAnswer(@PathVariable Integer id, @RequestBody List<Response> responses) {
//        return questionService.calculateResult(id, responses);
//    }

    @GetMapping("/result/{id}")
    public ResponseEntity<Integer> getTotalResult(@PathVariable Integer id, @RequestBody List<Response> responses) {
        ResponseEntity<Integer> result = questionService.calculateResult(id, responses);

        return result;
    }
}