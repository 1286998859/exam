package com.hljit.examol.entity;

import lombok.Data;

@Data
public class ExamManage {

    private Integer examCode;

    private String description;

    private String source;

    private Integer paperId;

    private String examDate;

    private Integer totalTime;



    private Integer totalScore;

    private String type;

    private String tips;
}