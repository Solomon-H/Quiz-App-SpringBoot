package com.demo.quizApp.controller;

import com.demo.quizApp.model.Question;
import com.demo.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        ResponseEntity<String> responseEntity = questionService.addQuestion(question);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CREATED).body(question);
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).body(null);
        }
    }

    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestions() {
        ResponseEntity<List<Question>> responseEntity = questionService.getAllQuestions();
        List<Question> questions = responseEntity.getBody();
        return ResponseEntity.status(responseEntity.getStatusCode()).body(questions);
}
    

    @GetMapping("/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable Long quizId) {
        List<Question> questions = questionService.findByQuizId(quizId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        ResponseEntity<List<Question>> responseEntity = questionService.getQuestionsByCategory(category);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

}
