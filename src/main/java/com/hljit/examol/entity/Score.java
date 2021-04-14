package com.hljit.examol.entity;

import lombok.Data;

@Data
public class Score {

    private Integer examCode;

    private Integer userId;

    private String subject;

    private Integer ptScore;

    private Integer etScore;

    private Integer score;

    private Integer scoreId;

    private String answerDate;
}
