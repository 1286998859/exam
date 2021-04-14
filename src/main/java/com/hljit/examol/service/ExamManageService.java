package com.hljit.examol.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.ExamManage;

import java.util.List;

public interface ExamManageService {


    List<ExamManage> findAll();
    IPage<ExamManage> findAll(Page<ExamManage> page);

    ExamManage findById(Integer examCode);

    int delete(Integer examCode);

    int update(ExamManage exammanage);


    ExamManage findOnlyPaperId();

    int add(ExamManage exammanage);
}
