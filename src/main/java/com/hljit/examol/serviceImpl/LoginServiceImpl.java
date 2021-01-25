package com.hljit.examol.serviceImpl;

import com.hljit.examol.entity.Admin;
import com.hljit.examol.entity.Student;
import com.hljit.examol.entity.Teacher;
import com.hljit.examol.entity.User;
import com.hljit.examol.mapper.LoginMapper;
import com.hljit.examol.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Admin adminLogin(Integer username, String password) {
        return loginMapper.adminLogin(username,password);
    }

    @Override
    public Teacher teacherLogin(Integer username, String password) {
        return loginMapper.teacherLogin(username,password);
    }

    @Override
    public Student studentLogin(Integer username, String password) {
        return loginMapper.studentLogin(username,password);
    }

    @Override
    public User userLogin(String username, String password) {
        return loginMapper.userLogin(username,password);
    }
}
