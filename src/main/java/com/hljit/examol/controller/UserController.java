package com.hljit.examol.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.ApiResult;
import com.hljit.examol.entity.User;
import com.hljit.examol.serviceImpl.UserServiceImpl;
import com.hljit.examol.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;



    @PutMapping("/userPWD")
    public ApiResult updatePwd(@RequestBody User user) {
        userService.updatePwd(user);
        return ApiResultHandler.buildApiResult(200,"密码更新成功",null);
    }

    @GetMapping("/users/{page}/{size}")
    public ApiResult findAll(@PathVariable Integer page, @PathVariable Integer size) {
        Page<User> userPage = new Page<>(page,size);
        IPage<User> res = userService.findAll(userPage);
        return  ApiResultHandler.buildApiResult(200,"分页查询所有学生",res);
    }

    @RequestMapping("/banUser/{id}")
    public ApiResult banUserById(@PathVariable Integer id){
        int res = userService.banUserById(id);
        return  ApiResultHandler.buildApiResult(200,"封禁成功",res);
    }

    @RequestMapping("/pickUser/{id}")
    public ApiResult pickUserById(@PathVariable Integer id){
        int res = userService.pickUserById(id);
        return  ApiResultHandler.buildApiResult(200,"解封成功",res);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ApiResult deleteUserById(@PathVariable Integer id){
        int res = userService.deleteUserById(id);
        return  ApiResultHandler.buildApiResult(200,"删除成功",res);
    }
}
