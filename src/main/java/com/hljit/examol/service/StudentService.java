package com.hljit.examol.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.dto.DiscussUser;
import com.hljit.examol.entity.DiscussPost;
import com.hljit.examol.entity.Student;

public interface StudentService {

    IPage<Student> findAll(Page<Student> page);

    IPage<DiscussPost> findDiscuss(Page<DiscussPost> page);

    IPage<DiscussPost> findDiscuss2(Page<DiscussPost> page);

    Student findById(Integer studentId);

    int deleteById(Integer studentId);

    int update(Student student);

    int updatePwd(Student student);
    int add(Student student);
}
