package com.dpwgc.document.center.app.query.comment;

import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.comment.CommentDTO;
import com.dpwgc.document.center.sdk.model.comment.CommentQuery;
import com.dpwgc.document.center.sdk.model.comment.sub.SubCommentDTO;
import com.dpwgc.document.center.sdk.model.comment.sub.SubCommentQuery;

import java.util.List;

public interface CommentQueryService {

    PageBase<List<CommentDTO>> queryComment(CommentQuery commentQuery);

    PageBase<List<SubCommentDTO>> querySubComment(SubCommentQuery subCommentQuery);
}
