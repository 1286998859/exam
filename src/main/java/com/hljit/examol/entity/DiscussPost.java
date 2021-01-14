package com.hljit.examol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DiscussPost implements Serializable {
    private static final long serialVersionUID = -90583991338816189L;

    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    /**
     * 0-普通; 1-置顶;
     */
    private Integer type;

    /**
     * 0-正常; 1-精华; 2-拉黑;
     */
    private Integer status;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Integer commentCount;

    private Double score;

    private User user;
}
