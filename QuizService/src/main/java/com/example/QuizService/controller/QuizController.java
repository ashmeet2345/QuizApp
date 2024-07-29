package com.example.QuizService.controller;


import com.example.QuizService.feign.QuizInterface;
import com.example.QuizService.model.QuestionWrapper;
import com.example.QuizService.model.QuizDto;
import com.example.QuizService.model.Response;
import com.example.QuizService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper> > getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> response){
        return quizService.calculateResult(id,response);
    }
}
