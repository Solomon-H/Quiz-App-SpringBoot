package com.demo.quizApp.service;

import com.demo.quizApp.dao.QuestionDao;
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
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Question> optional = questionDao.findById(id);
        if (optional.isPresent()) {
            Question question = optional.get();
            List<Question> questions = question.getQuestions();

            if (!questions.isEmpty()) {
                int right = 0;
                int i = 0;
                for (Response response : responses) {
                    if (response.getCorrectAnswer().equals(questions.get(i).getAnswer())) {
                        right++;
                    }
                    i++;
                }
                return new ResponseEntity<>(right, HttpStatus.OK);
            } else {
                // Handle the case when there are no questions
                return new ResponseEntity<>(0, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

