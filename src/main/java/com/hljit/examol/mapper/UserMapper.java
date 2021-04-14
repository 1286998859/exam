package com.hljit.examol.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {


    @Select("select * from user where user.id = #{id}")
    User queryUserById(int id);

    @Update("update exam.user set password = #{password} where id = #{id}")
    int updatePwd(User user);

    @Select("select * from exam.user")
    IPage<User> findAll(Page<User> page);

    @Update("update exam.user set status = 0 where id = #{id}")
    int banUserById(Integer id);

    @Update("update exam.user set status = 1 where id = #{id}")
    int pickUserById(Integer id);

    @Delete("delete from exam.user where id = #{id}")
    int deleteUserById(Integer id);

    @Select("select * from  exam.user where  username like concat('%',#{keyword},'%') order by type desc, create_time desc")
    IPage<User> findUserByKeyword(@Param("userPage") Page<User> userPage, @Param("keyword") String keyword);
}
