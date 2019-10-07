package hhx.dao;

import hhx.entity.Comment;

import java.util.List;

public interface CommentDao {
    int insertComment(Comment comment);
    List<Comment> queryCommentsBySkuId(int skuId);
}
