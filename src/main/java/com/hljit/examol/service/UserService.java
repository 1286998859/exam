package com.hljit.examol.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.User;

import java.util.Map;

public interface UserService {

    /**
     * 根据 id 查询用户
     *
     * @param id
     * @return
     */
    User queryUserById(int id);

    /**
     * 根据用户名查询用户
     *
     * @param name
     * @return
     */
    User queryUserByName(String name);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    Map<String, Object> register(User user);

    /**
     * 激活逻辑
     *
     * @param userId
     * @param code   激活码
     * @return
     */
    Integer activation(int userId, String code);

    /**
     * 登录验证, 返回失败信息或登录凭证
     *
     * @param username
     * @param password
     * @param expiredSecond
     * @return
     */
    Map<String, Object> login(String username, String password, int expiredSecond);

    /**
     * 退出登录
     *
     * @param ticket
     */
    void logout(String ticket);

    /**
     * 查询登录凭证
     *
     * @param ticket
     * @return
     */
    //LoginTicket findLoginTicket(String ticket);

    /**
     * 更新（设置）用户头像地址
     *
     * @param userId
     * @param headerUrl
     * @return
     */
    int updateHeader(Integer userId, String headerUrl);

    int updatePwd(User user);

    IPage<User> findAll(Page<User> page);

    int banUserById(Integer id);

    int pickUserById(Integer id);

    int deleteUserById(Integer id);

    IPage<User> findUserByKeyword(Page<User> userPage, String keyword);
}
