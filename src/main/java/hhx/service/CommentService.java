package hhx.service;

import hhx.entity.Comment;

import java.util.List;

public interface CommentService {
    int insertComment(Comment comment);
    List<Comment> queryCommentsBySkuId(int skuId);
}
