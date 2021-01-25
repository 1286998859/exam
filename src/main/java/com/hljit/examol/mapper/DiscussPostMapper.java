package com.hljit.examol.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hljit.examol.entity.DiscussPost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    /**
     * 查询列表
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
     * @param userId 如果参数只有一个，而且可能要动态拼接 SQL (<if>等)，必须加 @Param 注解
     * @return
     */
    Integer selectDiscussPostRows(@Param("userId") int userId);

    /**
     * 增加帖子
     *
     * @param discussPost
     * @return
     */
    Integer insertDiscussPost(DiscussPost discussPost);

    /**
     * 查看帖子详情
     *
     * @param id
     * @return
     */
    @Select("select * from discuss_post where id = #{id}")
    DiscussPost selectDiscussPostById(@Param("id") String id);

    /**
     * 更新帖子评论数量
     *
     * @param id
     * @param commentCount
     * @return
     */
    Integer updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);

    @Insert("insert INTO discuss_post (user_id,title,content,type,status,create_time) VALUES (#{userId}," +
            "#{title},#{content},#{type},#{status},#{createTime})")
    int addDiscuss(DiscussPost discussPost);

    //@Select("select * from  discuss_post where status != 2 and title like concat('%',#{keyword},'%') order by type desc, create_time desc")
    @Select("<script>"
            + "select * from  discuss_post where status != 2"
            + "<if test='keyword!=null'>"
            + "and title like concat('%',#{keyword},'%')"
            + "</if>"
            + "order by type desc, create_time desc"
            + "</script>")
    IPage<DiscussPost> findDiscussByKeyword(@Param("discussPostPage") Page<DiscussPost> discussPostPage,@Param("keyword") String keyword);

    @Select("select * from  discuss_post where status != 2 order by type desc, create_time desc")
    IPage<DiscussPost> findDiscuss(Page<DiscussPost> discussPostPage);

    @Select("select * from  discuss_post  order by type desc, create_time desc")
    IPage<DiscussPost> findDiscussAll(Page page);

    @Update("update discuss_post set type = 1 where id = #{id}")
    int makeTop(Integer id);

    @Update("update discuss_post set type = 0 where id = #{id}")
    int cancelTop(Integer id);

    @Update("update discuss_post set status = 1 where id = #{id}")
    int makePerfect(Integer id);

    @Update("update discuss_post set status = 0 where id = #{id}")
    int cancelPerfect(Integer id);

    @Update("update discuss_post set status = 2 where id = #{id}")
    int makeBan(Integer id);

    @Update("update discuss_post set status = 0 where id = #{id}")
    int cancelBan(Integer id);
}
