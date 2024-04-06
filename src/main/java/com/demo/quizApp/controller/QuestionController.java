package com.demo.quizApp.controller;
import com.demo.quizApp.model.Question;
import com.demo.quizApp.model.Quiz;
import com.demo.quizApp.model.UserResponse;
import com.demo.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestions() {
        ResponseEntity<List<Question>> responseEntity = questionService.getAllQuestions();
        List<Question> questions = responseEntity.getBody();
        return ResponseEntity.status(responseEntity.getStatusCode()).body(questions);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Question> getQuestionsById(@PathVariable Long id) {
    //     Question question = questionService.findByQuestionId(id).orElse(null);
    //     return new ResponseEntity<Question>(question, HttpStatus.OK);
    // }

    
    @PostMapping("/newQuestion")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        ResponseEntity<String> responseEntity = questionService.addQuestion(question);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CREATED).body(question);
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).body(null);
        }
    }


    @PostMapping("/getScore")
    public ResponseEntity<Integer> submitQuestionAnswer(@RequestBody List<UserResponse> responses) {
        int score = questionService.calculateScore(responses);
        return ResponseEntity.ok(score);
    }

    @GetMapping("/getQuiz")
    public ResponseEntity<List<Quiz>> getQuiz() {
        return questionService.getQuiz();
    }


    @GetMapping("/{id}")
    public String deleteQuestionById(@PathVariable Long id) {
      questionService.deleteQuestion(id);
      return "Deleted Question!";
    }

}


