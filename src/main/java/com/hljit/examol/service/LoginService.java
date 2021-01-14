package com.hljit.examol.service;

import com.hljit.examol.entity.Admin;
import com.hljit.examol.entity.Student;
import com.hljit.examol.entity.Teacher;

public interface LoginService {

    public Admin adminLogin(Integer username, String password);

    public Teacher teacherLogin(Integer username, String password);

    public Student studentLogin(Integer username, String password);
}
