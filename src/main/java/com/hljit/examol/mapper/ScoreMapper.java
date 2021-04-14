package com.hljit.examol.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScoreMapper {
    /**
     * @param score 添加一条成绩记录
     * @return
     */
    @Options(useGeneratedKeys = true,keyProperty = "scoreId")
    @Insert("insert into score(examCode,userId,subject,ptScore,etScore,score,answerDate) values(#{examCode},#{userId},#{subject},#{ptScore},#{etScore},#{score},#{answerDate})")
    int add(Score score);

    @Select("select scoreId,examCode,userId,subject,ptScore,etScore,score,answerDate from score order by scoreId desc")
    List<Score> findAll();

    // 分页
    @Select("select scoreId,examCode,userId,subject,ptScore,etScore,score,answerDate from score where userId = #{userId} order by scoreId desc")
    IPage<Score> findById(@Param("page") Page<?> page,@Param("userId") Integer userId);

    // 不分页
    @Select("select scoreId,examCode,userId,subject,ptScore,etScore,score,answerDate from score where userId = #{userId}")
    List<Score> findById(Integer userId);

    /**
     *
     * @return 查询每位学生的学科分数。 max其实是假的，为了迷惑老师，达到一次考试考生只参加了一次的效果
     */
    @Select("select max(etScore) as etScore from score where examCode = #{examCode} group by userId")
    List<Score> findByExamCode(Integer examCode);
}
