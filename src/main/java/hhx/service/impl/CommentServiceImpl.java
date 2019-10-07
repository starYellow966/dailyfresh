package hhx.service.impl;

import hhx.dao.CommentDao;
import hhx.entity.Comment;
import hhx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public int insertComment(Comment comment) {
        if (comment != null && comment.getComment() != null && comment.getSkuId() != null
                && comment.getUserId() != null && comment.getOrderId() != null)
            return commentDao.insertComment(comment);
        else
            return -1;
    }

    @Override
    public List<Comment> queryCommentsBySkuId(int skuId) {
        if (skuId < 0)
            return null;

        return commentDao.queryCommentsBySkuId(skuId);
    }
}
