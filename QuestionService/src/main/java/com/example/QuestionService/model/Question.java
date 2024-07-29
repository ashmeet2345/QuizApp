package com.example.QuestionService.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="DIFFICULTY")
    private String difficulty;

    @Column(name="OPTION_1")
    private String option_1;

    @Column(name="OPTION_2")
    private String option_2;

    @Column(name="OPTION_3")
    private String option_3;

    @Column(name="OPTION_4")
    private String option_4;

    @Column(name="QUESTION_TITLE")
    private String question_title;

    @Column(name="RIGHT_ANSWER")
    private String right_answer;
}
