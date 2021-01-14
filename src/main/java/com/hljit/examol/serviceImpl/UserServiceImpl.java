package com.hljit.examol.serviceImpl;

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
}
