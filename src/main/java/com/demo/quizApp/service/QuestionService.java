package com.demo.quizApp.service;

import com.demo.quizApp.dao.QuestionRepository;
import com.demo.quizApp.model.Question;
import com.demo.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = questionRepository.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<Question> findByQuestionId(Long id) {
        try {
            return questionRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            List<Question> questions = questionRepository.findByCategory(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepository.save(question);
            return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public int calculateScore(List<Response> responses) {
        int score = 0;

        for (Response response : responses) {
            if (isResponseCorrect(response)) {
                score++;
            }
        }

        return score;
    }

    
    private boolean isResponseCorrect(Response response) {
        Optional<Question> question = questionRepository.findById(response.getQuestionId());
    
        if (question.isPresent()) {
            Question questionEntity = question.get();
            System.out.println("Question: " + questionEntity.getQuestion());
            System.out.println("User's Response: " + response.getSelectedAnswers());
            System.out.println("Correct Answer: " + questionEntity.getChoice1());
    
            boolean correct = questionEntity.getChoice1().equals(response.getSelectedAnswers());
            System.out.println("Is response correct? " + correct);
    
            return correct;
        }
    
        return false;
    }
    
}
