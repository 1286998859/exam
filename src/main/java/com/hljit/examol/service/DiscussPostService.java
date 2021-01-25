package com.hljit.examol.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.DiscussPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscussPostService {

    /**
     * 查询
     *
     * @param userId 可选值, 用户 id
     * @param offset 起始数据号
     * @param limit  每页数据条数
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    IPage<DiscussPost> selectDiscussPosts2(Page page);

    /**
     * 查询行数
     *
     * @param userId 如果参数只有一个，而且可能要动态拼接SQL，必须加 @Param 注解
     * @return
     */
    Integer selectDiscussPostRows(@Param("userId") int userId);

    /**
     * 发布帖子
     *
     * @param post
     * @return
     */
    Integer insertDiscussPost(DiscussPost post);

    /**
     * 查看帖子详情
     *
     * @param id
     * @return
     */
    DiscussPost selectDiscussPostById(String id);

    /**
     * 更新帖子评论数量
     *
     * @param id
     * @param commentCount
     * @return
     */
    Integer updateCommentCount(int id, int commentCount);

    int addDiscuss(DiscussPost discussPost);

    IPage<DiscussPost> findDiscussByKeyword(Page<DiscussPost> discussPostPage, String keyword);

    IPage<DiscussPost> findDiscuss(Page<DiscussPost> discussPostPage);

    IPage<DiscussPost> findDiscussAll(Page<DiscussPost> discussPostPage);

    int makeTop(Integer id);
    int cancelTop(Integer id);

    int makePerfect(Integer id);

    int cancelPerfect(Integer id);

    int makeBan(Integer id);

    int cancelBan(Integer id);
}
