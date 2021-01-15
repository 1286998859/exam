package com.hljit.examol.controller;

import com.hljit.examol.entity.ApiResult;
import com.hljit.examol.entity.Comment;
import com.hljit.examol.serviceImpl.CommentServiceImpl;
import com.hljit.examol.serviceImpl.DiscussPostServiceImpl;
import com.hljit.examol.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    DiscussPostServiceImpl discussPostService;



    @PostMapping("/add/{discussPostId}")
    public ApiResult addComment(@PathVariable("discussPostId") int discussPostId,@RequestBody Comment comment) {

        comment.setStatus(0);
        comment.setCreateTime(new Date());
        if (comment.getTargetId() == null) {
            comment.setTargetId(0);
        }
        Integer integer = commentService.insertComment(comment);
        if(integer > 0){
            return ApiResultHandler.buildApiResult(200,"发布成功",null);
        }else {
            return ApiResultHandler.buildApiResult(400,"发布失败",null);
        }



    }

}
