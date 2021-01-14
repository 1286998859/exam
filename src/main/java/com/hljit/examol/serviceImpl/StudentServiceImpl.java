package com.hljit.examol.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.dto.DiscussUser;
import com.hljit.examol.entity.DiscussPost;
import com.hljit.examol.entity.Student;
import com.hljit.examol.mapper.StudentMapper;
import com.hljit.examol.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public IPage<Student> findAll(Page<Student> page) {
        return studentMapper.findAll(page);
    }

    @Override
    public IPage<DiscussPost> findDiscuss(Page<DiscussPost> page) {
        return studentMapper.findDiscuss(page);
    }

    @Override
    public IPage<DiscussPost> findDiscuss2(Page<DiscussPost> page) {
        return studentMapper.findDiscuss2(page);
    }

    @Override
    public Student findById(Integer studentId) {
        return studentMapper.findById(studentId);
    }

    @Override
    public int deleteById(Integer studentId) {
        return studentMapper.deleteById(studentId);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public int updatePwd(Student student) {
        return studentMapper.updatePwd(student);
    }

    @Override
    public int add(Student student) {
        return studentMapper.add(student);
    }
}
