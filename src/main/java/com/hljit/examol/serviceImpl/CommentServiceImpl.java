package com.hljit.examol.serviceImpl;

import com.hljit.examol.entity.Comment;
import com.hljit.examol.mapper.CommentMapper;
import com.hljit.examol.service.CommentService;
import com.hljit.examol.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    CommentMapper commentMapper;



    @Autowired
    DiscussPostService discussPostService;

    @Override
    public List<Comment> selectCommentsByEntity(int entityType, int entityId) {
        return commentMapper.selectCommentsByEntity(entityType, entityId);
    }

    @Override
    public List<Comment> selectCommentsByEntityPost(int entityId) {
        return commentMapper.selectCommentsByEntityPost(entityId);
    }

    @Override
    public List<Comment> selectCommentsByEntityComment(int entityId) {
        return commentMapper.selectCommentsByEntityComment(entityId);
    }

    @Override
    public Integer selectCountByEntity( int entityId) {
        return commentMapper.selectCountByEntity( entityId);
    }

    @Override
    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Integer insertComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        // 过滤 HTML 标记和敏感词
        comment.setContent(comment.getContent());
        comment.setContent(comment.getContent());
        // 添加评论
        int rows = commentMapper.insertComment(comment);

        // 更新帖子的评论数量
        if (comment.getEntityType() == 1) {
            Integer count = commentMapper.selectCountByEntity(comment.getEntityId());
            discussPostService.updateCommentCount(comment.getEntityId(), count);
        }
        return rows;
    }

    @Override
    public Comment selectCommentById(int id) {
        return commentMapper.selectCommentById(id);
    }


}
