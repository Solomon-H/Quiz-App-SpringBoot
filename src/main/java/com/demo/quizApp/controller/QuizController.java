package com.demo.quizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.quizApp.dao.QuizRepository;
import com.demo.quizApp.model.Quiz;
import com.demo.quizApp.model.Response;
import com.demo.quizApp.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
        ResponseEntity<String> response = quizService.createQuiz(quiz);
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @GetMapping("/all-quizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long quizId, @RequestBody List<Response> responses) {
        int score = quizService.calculateScore(quizId, responses);
        return ResponseEntity.ok(score);
    }
}
