package com.hljit.examol.entity;

import lombok.Data;

@Data
public class MultiQuestion {

    private Integer questionId;

    private String subject;

    private String section;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String question;

    private String level;

    private String rightAnswer;

    private String analysis;

    private Integer score;
}
