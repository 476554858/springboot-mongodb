package com.zjx.article;

import com.zjx.article.po.Comment;
import com.zjx.article.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void testSaveComment() {
        Comment comment = new Comment();
        comment.setId("1");
        comment.setArticleid("10000");
        comment.setContent("测试添加数据");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setUserid("1003");
        comment.setNickname("此乃良民");
        comment.setState("1");
        comment.setLikenum(0);
        comment.setReplynum(0);
        comment.setParentid("6");
        commentService.saveComment(comment);
    }

    @Test
    public void testFindCommentList() {
        List<Comment> commentList = commentService.findCommentList();
        System.out.println(commentList);
    }

    @Test
    public void testFindCommentById() {
        Comment comment = commentService.findCommentById("1");
        System.out.println(comment);
    }

    @Test
    public void testFindCommentListByParentid() {
        Page<Comment> page = commentService.findCommentListByParentid("7", 1, 2);
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());
    }

    @Test
    public void testUpdateCommentListnum() {
        commentService.updateCommentLikenum("1");
    }

}
