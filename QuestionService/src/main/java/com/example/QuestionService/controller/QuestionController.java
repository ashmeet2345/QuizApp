package com.example.QuestionService.controller;


import com.example.QuestionService.model.Question;
import com.example.QuestionService.model.QuestionWrapper;
import com.example.QuestionService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.QuestionService.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question> > getAllQuestions(){
        return questionService.getAllQuestions();
        //ResponseEntity is used to return data as well as status code to the client
    }

    @GetMapping("allQuestions/{category}")
    public ResponseEntity<List<Question> > getQuestionsByCategory(@PathVariable("category") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer> > getQuestionsForQuiz(@RequestParam String category,@RequestParam int numQuestions){
        return questionService.getQuestionsForQuiz(category,numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper> >  getQuestionsFromId(@RequestBody List<Integer> ids){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(ids);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        System.out.println(environment.getProperty("local.server.port"));
       return questionService.getScore(responses);
    }

}
