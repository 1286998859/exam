package com.hljit.examol.mapper;

import com.hljit.examol.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select * from user where user.id = #{id}")
    User queryUserById(int id);
}
