package com.hljit.examol.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.DiscussPost;
import com.hljit.examol.mapper.DiscussPostMapper;
import com.hljit.examol.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DiscussPostServiceImpl implements DiscussPostService {

    @Autowired(required = true)
    DiscussPostMapper discussPostMapper;

//    @Resource
//    SensitiveFilter sensitiveFilter;

    @Override
    public List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    @Override
    public IPage<DiscussPost> selectDiscussPosts2(Page page) {
        return discussPostMapper.selectDiscussPosts2(page);
    }

    @Override
    public Integer selectDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }

    @Override
    public Integer insertDiscussPost(DiscussPost post) {
        if (post == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 转义 HTML 标记
        post.setTitle(HtmlUtils.htmlEscape(post.getTitle()));
        post.setContent(HtmlUtils.htmlEscape(post.getContent()));
        // 过滤敏感词
        //post.setTitle(sensitiveFilter.filter(post.getTitle()));
        //post.setContent(sensitiveFilter.filter(post.getContent()));

        return discussPostMapper.insertDiscussPost(post);
    }



    @Override
    public DiscussPost selectDiscussPostById(String id) {
        return discussPostMapper.selectDiscussPostById(id);
    }

    @Override
    public Integer updateCommentCount(int id, int commentCount) {
        return discussPostMapper.updateCommentCount(id, commentCount);
    }

    public int addDiscuss(DiscussPost discussPost) {

        return discussPostMapper.addDiscuss(discussPost);
    }
}
