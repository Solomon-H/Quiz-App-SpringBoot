package com.demo.quizApp.service;

import com.demo.quizApp.model.Question;
import com.demo.quizApp.model.Quiz;
import com.demo.quizApp.model.UserResponse;
import com.demo.quizApp.repository.QuestionRepository;
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

    // public Optional<Question> findByQuestionId(Long id) {
    //     try {
    //         return questionRepository.findById(id);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return Optional.empty();
    //     }
    // }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepository.save(question);
            return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Quiz>> getQuiz() {
        List<Quiz> quizList = new ArrayList<>();
        List<Question> questionList = questionRepository.findAll();

        for (Question question : questionList) {
            Quiz quiz = new Quiz();
            quiz.setId(question.getId());
            quiz.setCategory(question.getCategory());
            quiz.setQuestion(question.getQuestion());
            quiz.setChoice1(question.getChoice1());
            quiz.setChoice2(question.getChoice2());
            quiz.setChoice3(question.getChoice3());
            quizList.add(quiz);
        }

        return new ResponseEntity<>(quizList, HttpStatus.OK);
    }

    public int calculateScore(List<UserResponse> responses) {
        int score = 0;
        for (UserResponse response : responses) {
            Optional<Question> question = questionRepository.findById(response.getQuestionId());
            if (question.isPresent() && question.get().getChoice1().equals(response.getResponse())) {
                score++;
            }
        }

        return score;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

}
