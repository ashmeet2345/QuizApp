package com.example.QuestionService.service;

import com.example.QuestionService.model.Question;
import com.example.QuestionService.model.QuestionWrapper;
import com.example.QuestionService.model.Response;
import com.example.QuestionService.repository.QuestionRepo;
import org.hibernate.cfg.Environment;
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
    QuestionRepo questionRepo;

    public ResponseEntity<List<Question> > getAllQuestions() {
       try{
           return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question> >getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionRepo.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }


    public ResponseEntity<List<Integer> > getQuestionsForQuiz(String category, int numQuestions) {
        List<Integer> questions=questionRepo.findRandomlyByCategory(category,numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> ids) {
        List<QuestionWrapper> questionWrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();
        for(Integer id:ids){
            questions.add(questionRepo.findById(id).orElse(null));
        }

        for(Question q:questions){
            questionWrappers.add(new QuestionWrapper(q.getId(),q.getOption_1(),q.getOption_2(),q.getOption_3(),q.getOption_4(),q.getQuestion_title()));
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        for(Response r:responses){
            Optional<Question> question=questionRepo.findById(r.getId());
            if(r.getResponse().equals(question.get().getRight_answer())){
                right++;
            }
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
