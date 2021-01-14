package com.hljit.examol.dto;

import com.hljit.examol.entity.DiscussPost;
import com.hljit.examol.entity.User;
import lombok.Data;

import java.io.Serializable;
@Data
public class DiscussUser implements Serializable {

    private DiscussPost discussPost;

    private User user;

}
