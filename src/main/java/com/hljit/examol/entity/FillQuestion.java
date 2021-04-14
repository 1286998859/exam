package com.hljit.examol.entity;

import lombok.Data;

@Data
public class FillQuestion {

    private Integer questionId;

    private String subject;

    private String question;

    private String answer;

    private Integer score;

    private String level;

    private String section;

    private String analysis;
}
