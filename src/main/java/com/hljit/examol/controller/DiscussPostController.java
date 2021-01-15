package com.hljit.examol.controller;

import com.hljit.examol.entity.ApiResult;
import com.hljit.examol.entity.Comment;
import com.hljit.examol.entity.DiscussPost;
import com.hljit.examol.entity.User;
import com.hljit.examol.serviceImpl.CommentServiceImpl;
import com.hljit.examol.serviceImpl.DiscussPostServiceImpl;
import com.hljit.examol.serviceImpl.UserServiceImpl;
import com.hljit.examol.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class DiscussPostController {

    @Autowired
    private DiscussPostServiceImpl discussPostServiceImpl;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CommentServiceImpl commentServiceImpl;




    @PostMapping("/community/addDiscuss")
    public ApiResult addDiscuss(@RequestBody DiscussPost discussPost){
        discussPost.setCreateTime(new Date());
        discussPost.setStatus(0);
        discussPost.setType(0);
        if(discussPostServiceImpl.addDiscuss(discussPost) == 1){
            return ApiResultHandler.buildApiResult(200,"发布成功",null);
        }else {
            return ApiResultHandler.buildApiResult(400,"发布失败",null);
        }

    }

    @GetMapping("/post/{id}")
    public ApiResult queryDiscussById(@PathVariable("id") String id){

        DiscussPost res = discussPostServiceImpl.selectDiscussPostById(id);
        User user = userService.queryUserById(res.getUserId());
        //res.setUser(user);
        List list = new ArrayList();
        list.add(res);
        list.add(user);


        // 评论列表
        List<Comment> commentList = commentServiceImpl.selectCommentsByEntityPost( res.getId());
        // 评论 VO 列表
        List<Map<String, Object>> commentVoList = new ArrayList<>();
        if (commentList != null) {
            for (Comment comment : commentList) {
                // 评论 VO
                Map<String, Object> commentVo = new HashMap<>();
                // 评论
                commentVo.put("comment", comment);
                // 作者
                commentVo.put("user", userService.queryUserById(comment.getUserId()));



                // 回复列表
                List<Comment> replyList = commentServiceImpl.selectCommentsByEntityComment(comment.getId());
                // 回复 VO 列表
                List<Map<String, Object>> replyVoList = new ArrayList<>();
                if (replyList != null) {
                    for (Comment reply : replyList) {
                        Map<String, Object> replyVo = new HashMap<>();
                        // 回复
                        replyVo.put("reply", reply);
                        // 作者
                        replyVo.put("user", userService.queryUserById(reply.getUserId()));
                        // 回复目标
                        User target = reply.getTargetId() == 0 ? null : userService.queryUserById(reply.getTargetId());
                        replyVo.put("target", target);

                        replyVoList.add(replyVo);
                    }
                }

                // 回复
                commentVo.put("replys", replyVoList);
                // 回复数量

                //int replyCount = commentServiceImpl.selectCountByEntityComment(comment.getId());
                //commentVo.put("replyCount", replyCount);
                commentVoList.add(commentVo);
            }
        }
        list.add(commentVoList);

        if(null != res){
            //return ApiResultHandler.buildApiResult(200,"查询成功",res);
            return ApiResultHandler.buildApiResult(200,"查询成功",list);
        }else {
            return ApiResultHandler.buildApiResult(400,"发布失败",null);
        }

    }


}
