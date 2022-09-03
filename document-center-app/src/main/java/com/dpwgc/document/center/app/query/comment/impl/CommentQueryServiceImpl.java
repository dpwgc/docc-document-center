package com.dpwgc.document.center.app.query.comment.impl;

import com.dpwgc.document.center.app.query.comment.CommentQueryService;
import com.dpwgc.document.center.sdk.model.comment.CommentDTO;
import com.dpwgc.document.center.sdk.model.comment.CommentQuery;
import com.dpwgc.document.center.sdk.model.comment.sub.SubCommentDTO;
import com.dpwgc.document.center.sdk.model.comment.sub.SubCommentQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentQueryServiceImpl implements CommentQueryService {

    @Override
    public List<CommentDTO> queryComment(CommentQuery commentQuery) {
        return null;
    }

    @Override
    public List<SubCommentDTO> querySubComment(SubCommentQuery subCommentQuery) {
        return null;
    }
}
