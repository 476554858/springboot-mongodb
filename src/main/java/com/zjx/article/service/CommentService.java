package com.zjx.article.service;

import com.zjx.article.dao.CommentRepository;
import com.zjx.article.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存一个评论
     *
     * @param comment
     */
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 更新评论
     *
     * @param comment
     */
    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 根据id删除评论
     *
     * @param id
     */
    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
    }

    /**
     * 查询所有评论
     *
     * @return
     */
    public List<Comment> findCommentList() {
        return commentRepository.findAll();
    }

    /**
     * 根据id查询评论
     *
     * @param id
     * @return
     */
    public Comment findCommentById(String id) {
        return commentRepository.findById(id).get();
    }

    /**
     * 分页查询
     *
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Comment> findCommentListByParentid(String parentid, int page, int size) {
        return commentRepository.findByParentid(parentid, PageRequest.of(page - 1, size));
    }

    /**
     * 评论点赞数累加
     *
     * @param id
     */
    public void updateCommentLikenum(String id) {
        // 查询条件
        Query query = Query.query(Criteria.where("_id").is(id));
        // 更新条件
        Update update = new Update();
        update.inc("likenum");
        mongoTemplate.updateFirst(query, update, Comment.class);
    }

}
