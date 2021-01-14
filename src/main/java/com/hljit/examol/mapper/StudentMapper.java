package com.hljit.examol.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.dto.DiscussUser;
import com.hljit.examol.entity.DiscussPost;
import com.hljit.examol.entity.Student;
import org.apache.ibatis.annotations.*;
@Mapper
public interface StudentMapper {

    /**
     * 分页查询所有学生
     * @param page
     * @return List<Student>
     */
    @Select("select * from student")
    IPage<Student> findAll(Page page);

    @Select("select * from  discuss_post where status != 2 order by type desc, create_time desc")
    IPage<DiscussPost> findDiscuss(Page page);

    @Select("select discuss_post.*,user1.* from  discuss_post,user1  where discuss_post.user_id = user1.id AND discuss_post.status != 2 order by discuss_post.type desc, discuss_post.create_time desc")
    IPage<DiscussPost> findDiscuss2(Page page);

    @Select("select * from student where studentId = #{studentId}")
    Student findById(Integer studentId);

    @Delete("delete from student where studentId = #{studentId}")
    int deleteById(Integer studentId);

    /**
     *更新所有学生信息
     * @param student 传递一个对象
     * @return 受影响的记录条数
     */
    @Update("update student set studentName = #{studentName},grade = #{grade},major = #{major},clazz = #{clazz}," +
            "institute = #{institute},tel = #{tel},email = #{email},pwd = #{pwd},cardId = #{cardId},sex = #{sex},role = #{role} " +
            "where studentId = #{studentId}")
    int update(Student student);

    /**
     * 更新密码
     * @param student
     * @return 受影响的记录条数
     */
    @Update("update student set pwd = #{pwd} where studentId = #{studentId}")
    int updatePwd(Student student);


    @Options(useGeneratedKeys = true,keyProperty = "studentId")
    @Insert("insert into student(studentName,grade,major,clazz,institute,tel,email,pwd,cardId,sex,role) values " +
            "(#{studentName},#{grade},#{major},#{clazz},#{institute},#{tel},#{email},#{pwd},#{cardId},#{sex},#{role})")
    int add(Student student);


}
