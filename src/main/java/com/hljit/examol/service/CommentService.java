package com.hljit.examol.service;

import com.hljit.examol.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {

    /**
     * 根据评论对象实体分页查询评论列表
     *
     * @param entityType 评论对象，评论或者帖子 对帖子评论或对评论进行评论
     * @param entityId   评论对象 id
     * @return
     */
    List<Comment> selectCommentsByEntity(@Param("entityType")int entityType,@Param("entityId") int entityId);

    List<Comment> selectCommentsByEntityPost( int entityId);

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
    Comment selectCommentById(int id);
}
