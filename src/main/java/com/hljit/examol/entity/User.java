package com.hljit.examol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 708453489701305063L;

    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String email;
    /**
     * 0-普通用户; 1-超级管理员; 2-版主;
     */
    private Integer type;
    /**
     * 0-未激活; 1-已激活;
     */
    private Integer status;

    private String activationCode;

    private String headerUrl;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
}
