package com.hljit.examol.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.User;
import com.hljit.examol.mapper.UserMapper;
import com.hljit.examol.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User queryUserById(int id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public User queryUserByName(String name) {
        return null;
    }

    @Override
    public Map<String, Object> register(User user) {
        return null;
    }

    @Override
    public Integer activation(int userId, String code) {
        return null;
    }

    @Override
    public Map<String, Object> login(String username, String password, int expiredSecond) {
        return null;
    }

    @Override
    public void logout(String ticket) {

    }

    @Override
    public int updateHeader(Integer userId, String headerUrl) {
        return 0;
    }

    @Override
    public int updatePwd(User user) {
        return userMapper.updatePwd(user);
    }

    @Override
    public IPage<User> findAll(Page<User> page) {
        return userMapper.findAll(page);
    }

    @Override
    public int banUserById(Integer id) {
        return userMapper.banUserById(id);
    }

    @Override
    public int pickUserById(Integer id) {
        return userMapper.pickUserById(id);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }
}
