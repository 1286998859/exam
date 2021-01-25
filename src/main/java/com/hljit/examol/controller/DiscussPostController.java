package com.hljit.examol.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.ApiResult;
import com.hljit.examol.entity.Comment;
import com.hljit.examol.entity.DiscussPost;
import com.hljit.examol.entity.User;
import com.hljit.examol.serviceImpl.CommentServiceImpl;
import com.hljit.examol.serviceImpl.DiscussPostServiceImpl;
import com.hljit.examol.serviceImpl.StudentServiceImpl;
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

    @Autowired
    private StudentServiceImpl studentService;



    @GetMapping("/discussPostsByKeyword/{page}/{size}/{keyword}")
    public ApiResult findDiscussByKeyword(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String keyword){
        Page<DiscussPost> discussPostPage = new Page<>(page,size);
        IPage<DiscussPost> res = discussPostServiceImpl.findDiscussByKeyword(discussPostPage,keyword);
        List<DiscussPost> records = res.getRecords();
        for (DiscussPost dis: records) {
            User user = userService.queryUserById(dis.getUserId());
            dis.setUser(user);
        }

        return  ApiResultHandler.buildApiResult(200,"分页搜索查询贴子",res);
    }

    @GetMapping("/discussPostsByKeyword/{page}/{size}")
    public ApiResult findDiscussByKeyword2(@PathVariable Integer page, @PathVariable Integer size){
        Page<DiscussPost> discussPostPage = new Page<>(page,size);
        IPage<DiscussPost> res = discussPostServiceImpl.findDiscuss(discussPostPage);
        List<DiscussPost> records = res.getRecords();
        for (DiscussPost dis: records) {
            User user = userService.queryUserById(dis.getUserId());
            dis.setUser(user);
        }

        return  ApiResultHandler.buildApiResult(200,"分页搜索查询贴子",res);
    }

    @GetMapping("/discussPostAll/{page}/{size}")
    public ApiResult findDiscussAll(@PathVariable Integer page, @PathVariable Integer size){
        Page<DiscussPost> discussPostPage = new Page<>(page,size);
        IPage<DiscussPost> res = discussPostServiceImpl.findDiscussAll(discussPostPage);
        List<DiscussPost> records = res.getRecords();
        for (DiscussPost dis: records) {
            User user = userService.queryUserById(dis.getUserId());
            dis.setUser(user);
        }
        return  ApiResultHandler.buildApiResult(200,"分页查询贴子",res);
    }



    @PostMapping("/community/addDiscuss")
    public ApiResult addDiscuss(@RequestBody DiscussPost discussPost){
        discussPost.setCreateTime(new Date());
        discussPost.setStatus(0);
        discussPost.setType(0);
        discussPost.setCommentCount(0);
        discussPost.setScore(0.0);
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


    @RequestMapping("/makeTop/{id}")
    public ApiResult makeTop(@PathVariable Integer id){
        int res = discussPostServiceImpl.makeTop(id);
        return  ApiResultHandler.buildApiResult(200,"置顶成功",res);
    }

    @RequestMapping("/cancelTop/{id}")
    public ApiResult cancelTop(@PathVariable Integer id){
        int res = discussPostServiceImpl.cancelTop(id);
        return  ApiResultHandler.buildApiResult(200,"取消置顶成功",res);
    }

    @RequestMapping("/makePerfect/{id}")
    public ApiResult makePerfect(@PathVariable Integer id){
        int res = discussPostServiceImpl.makePerfect(id);
        return  ApiResultHandler.buildApiResult(200,"加精成功",res);
    }

    @RequestMapping("/cancelPerfect/{id}")
    public ApiResult cancelPerfect(@PathVariable Integer id){
        int res = discussPostServiceImpl.cancelPerfect(id);
        return  ApiResultHandler.buildApiResult(200,"取消加精成功",res);
    }

    @RequestMapping("/makeBan/{id}")
    public ApiResult makeBan(@PathVariable Integer id){
        int res = discussPostServiceImpl.makeBan(id);
        return  ApiResultHandler.buildApiResult(200,"拉黑成功",res);
    }

    @RequestMapping("/cancelBan/{id}")
    public ApiResult cancelBan(@PathVariable Integer id){
        int res = discussPostServiceImpl.cancelBan(id);
        return  ApiResultHandler.buildApiResult(200,"取消拉黑成功",res);
    }


}
