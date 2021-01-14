package com.hljit.examol.controller;

import com.hljit.examol.entity.ApiResult;
import com.hljit.examol.entity.DiscussPost;
import com.hljit.examol.entity.User;
import com.hljit.examol.serviceImpl.DiscussPostServiceImpl;
import com.hljit.examol.serviceImpl.UserServiceImpl;
import com.hljit.examol.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class DiscussPostController {

    @Autowired
    private DiscussPostServiceImpl discussPostService;

    @Autowired
    private UserServiceImpl userService;




    @PostMapping("/community/addDiscuss")
    public ApiResult addDiscuss(@RequestBody DiscussPost discussPost){
        discussPost.setCreateTime(new Date());
        discussPost.setStatus(0);
        discussPost.setType(0);
        if(discussPostService.addDiscuss(discussPost) == 1){
            return ApiResultHandler.buildApiResult(200,"发布成功",null);
        }else {
            return ApiResultHandler.buildApiResult(400,"发布失败",null);
        }

    }

    @GetMapping("/post/{id}")
    public ApiResult queryDiscussById(@PathVariable("id") String id){

        DiscussPost res = discussPostService.selectDiscussPostById(id);
        User user = userService.queryUserById(res.getUserId());
        res.setUser(user);

        if(null != res){
            return ApiResultHandler.buildApiResult(200,"查询成功",res);
        }else {
            return ApiResultHandler.buildApiResult(400,"发布失败",null);
        }

    }


}
