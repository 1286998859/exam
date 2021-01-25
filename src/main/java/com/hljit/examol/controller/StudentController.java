package com.hljit.examol.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.dto.DiscussUser;
import com.hljit.examol.entity.ApiResult;
import com.hljit.examol.entity.DiscussPost;
import com.hljit.examol.entity.Student;
import com.hljit.examol.entity.User;
import com.hljit.examol.serviceImpl.DiscussPostServiceImpl;
import com.hljit.examol.serviceImpl.StudentServiceImpl;
import com.hljit.examol.serviceImpl.UserServiceImpl;
import com.hljit.examol.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private DiscussPostServiceImpl discussPostService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/students/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Student> studentPage = new Page<>(page,size);
        IPage<Student> res = studentService.findAll(studentPage);
        return  ApiResultHandler.buildApiResult(200,"分页查询所有学生",res);
    }

    @GetMapping("/discussPosts/{page}/{size}")
    public ApiResult findDiscuss(@PathVariable Integer page, @PathVariable Integer size){
        Page<DiscussPost> discussPostPage = new Page<>(page,size);
        IPage<DiscussPost> res = studentService.findDiscuss(discussPostPage);
        List<DiscussPost> records = res.getRecords();
        for (DiscussPost dis: records) {
            User user = userService.queryUserById(dis.getUserId());
            dis.setUser(user);
        }
        return  ApiResultHandler.buildApiResult(200,"分页查询贴子",res);
    }

    @GetMapping("/discussPosts2/{page}/{size}")
    public ApiResult findDiscuss2(@PathVariable Integer page, @PathVariable Integer size){
        Page<DiscussPost> discussPostPage = new Page<>(page,size);
        IPage<DiscussPost> res = discussPostService.selectDiscussPosts2(discussPostPage);
        return  ApiResultHandler.buildApiResult(200,"分页查询贴子",res);
    }

    @GetMapping("/student/{studentId}")
    public ApiResult findById(@PathVariable("studentId") Integer studentId) {
        Student res = studentService.findById(studentId);
        if (res != null) {
            return ApiResultHandler.buildApiResult(200,"请求成功",res);
        } else {
            return ApiResultHandler.buildApiResult(404,"查询的用户不存在",null);
        }
    }

    @DeleteMapping("/student/{studentId}")
    public ApiResult deleteById(@PathVariable("studentId") Integer studentId) {
        return ApiResultHandler.buildApiResult(200,"删除成功",studentService.deleteById(studentId));
    }

    @PutMapping("/studentPWD")
    public ApiResult updatePwd(@RequestBody Student student) {
        studentService.updatePwd(student);
        return ApiResultHandler.buildApiResult(200,"密码更新成功",null);
    }
    @PutMapping("/student")
    public ApiResult update(@RequestBody Student student) {
        int res = studentService.update(student);
        if (res != 0) {
            return ApiResultHandler.buildApiResult(200,"更新成功",res);
        }
        return ApiResultHandler.buildApiResult(400,"更新失败",res);
    }

    @PostMapping("/student")
    public ApiResult add(@RequestBody Student student) {
        int res = studentService.add(student);
        if (res == 1) {
            return ApiResultHandler.buildApiResult(200,"添加成功",null);
        }else {
            return ApiResultHandler.buildApiResult(400,"添加失败",null);
        }
    }
}
