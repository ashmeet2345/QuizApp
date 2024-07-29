package com.example.QuizService.service;

import com.example.QuizService.feign.QuizInterface;
import com.example.QuizService.model.QuestionWrapper;
import com.example.QuizService.model.Quiz;
import com.example.QuizService.model.Response;
import com.example.QuizService.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;


    @Autowired
    QuizInterface quizInterface;

    /*@Autowired
    private QuestionRepo questionRepo;*/

    public ResponseEntity<String> createQuiz(String category, int numQ){

        List<Integer> questions=quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setQuestion(questions);
        quizRepo.save(quiz);

        return new ResponseEntity("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz= quizRepo.findById(id);
        List<QuestionWrapper> questions= quizInterface.getQuestionsFromId(quiz.get().getQuestion()).getBody();

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {

        ResponseEntity<Integer> score= quizInterface.getScore(responses);
        return score;
    }
}
