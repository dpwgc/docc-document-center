package com.dpwgc.document.center.infrastructure.repository.comment;

import com.dpwgc.document.center.domain.comment.sub.SubComment;
import com.dpwgc.document.center.domain.comment.sub.SubCommentRepository;
import com.dpwgc.document.center.infrastructure.dal.comment.mapper.SubCommentMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SubCommentRepositoryImpl implements SubCommentRepository {

    @Resource
    SubCommentMapper subCommentMapper;

    public String createSubComment(SubComment subComment) {
        return null;
    }

    public Boolean updateSubComment(SubComment subComment) {
        return null;
    }
}
