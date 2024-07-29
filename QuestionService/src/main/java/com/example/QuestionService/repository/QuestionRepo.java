package com.example.QuestionService.repository;


import com.example.QuestionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value ="SELECT Q.ID FROM QUESTION Q WHERE Q.CATEGORY=:category LIMIT :num" ,nativeQuery = true)
    List<Integer> findRandomlyByCategory(String category,int num);
}
