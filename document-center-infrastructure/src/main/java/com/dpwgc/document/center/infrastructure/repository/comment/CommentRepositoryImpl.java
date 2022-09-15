package com.dpwgc.document.center.infrastructure.repository.comment;

import com.dpwgc.document.center.domain.comment.Comment;
import com.dpwgc.document.center.domain.comment.CommentRepository;
import com.dpwgc.document.center.infrastructure.dal.comment.mapper.CommentMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Resource
    CommentMapper commentMapper;

    public String createComment(Comment comment) {
        return null;
    }

    public Boolean updateComment(Comment comment) {
        return null;
    }
}
