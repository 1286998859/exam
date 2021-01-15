package com.hljit.examol.mapper;

import com.hljit.examol.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface CommentMapper {

//    @Select("select\n" +
//            "        id, user_id, entity_type, entity_id, target_id, content, status, create_time\n" +
//            "        from exam.comment\n" +
//            "        where status = 0\n" +
//            "        and 'entity_type' = #{entityType}\n" +
//            "        and 'entity_id' = #{entityId}\n" +
//            "        order by create_time asc")
    List<Comment> selectCommentsByEntity(@Param("entityType")int entityType,@Param("entityId") int entityId);


    @Select("select\n" +
            "        id, user_id, entity_type, entity_id, target_id, content, status, create_time\n" +
            "        from exam.comment\n" +
            "        where status = 0\n" +
            "        and entity_type = 1\n" +
            "        and entity_id = #{entityId}\n" +
            "        order by create_time asc")
    List<Comment> selectCommentsByEntityPost( int entityId);


    @Select("select\n" +
            "        id, user_id, entity_type, entity_id, target_id, content, status, create_time\n" +
            "        from exam.comment\n" +
            "        where status = 0\n" +
            "        and entity_type = 2\n" +
            "        and entity_id = #{entityId}\n" +
            "        order by create_time asc")
    List<Comment> selectCommentsByEntityComment( int entityId);

    /**
     * 查询评论条目数
     *
     *
     * @param entityId
     * @return
     */
    Integer selectCountByEntity( int entityId);

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    Integer insertComment(Comment comment);

    /**
     * 查询帖子根据 id
     *
     * @param id
     * @return
     */
    @Select("select `id`, `user_id`, `entity_type`, `entity_id`, `target_id`, `content`, `status`, `create_time` from community.comment where `id` = #{id}")
    Comment selectCommentById(int id);
}
