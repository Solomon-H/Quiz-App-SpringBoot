package com.demo.quizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.quizApp.dao.QuestionRepository;
import com.demo.quizApp.dao.QuizRepository;
import com.demo.quizApp.model.Question;
import com.demo.quizApp.model.Quiz;
import com.demo.quizApp.model.Response;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        try {
            List<Quiz> quizzes = quizRepository.findAll();
            return new ResponseEntity<>(quizzes, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> createQuiz(Quiz quiz) {
        try {
            Quiz savedQuiz = quizRepository.save(quiz);
            return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create quiz", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Quiz> getQuizById(Long id) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(id);
            if (quiz.isPresent()) {
                return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public int calculateScore(Long quizId, List<Response> responses) {
        int score = 0;
    
        List<Question> questions = questionRepository.findByQuizId(quizId);
    
        for (Response response : responses) {
            Optional<Question> matchedQuestion = questions.stream()
                .filter(q -> q.getId().equals(response.getQuestionId()))
                .findFirst();
    
            if (matchedQuestion.isPresent()) {
                Question question = matchedQuestion.get();
                if (response.getSelectedAnswers().equals(question.getAnswer())) {
                    score++;
                }
            }
        }
    
        return score;
    }
    



}
