package com.hljit.examol.mapper;

import com.hljit.examol.entity.Admin;
import com.hljit.examol.entity.Student;
import com.hljit.examol.entity.Teacher;
import com.hljit.examol.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select adminId,adminName,sex,tel,email,cardId,role from admin1 where adminId = #{username} and pwd = #{password}")
    public Admin adminLogin(@Param("username")Integer username, @Param("password")String password);

    @Select("select teacherId,teacherName,institute,sex,tel,email,cardId," +
            "type,role from teacher where teacherId = #{username} and pwd = #{password}")
    public Teacher teacherLogin(@Param("username")Integer username, @Param("password")String password);

    @Select("select studentId,studentName,grade,major,clazz,institute,tel," +
            "email,cardId,sex,role from student where studentId = #{username} and pwd = #{password}")
    public Student studentLogin(@Param("username")Integer username, @Param("password")String password);

    @Select("select * from exam.user where username = #{username} and password = #{password}")
    User userLogin(@Param("username") String username,@Param("password") String password);
}
