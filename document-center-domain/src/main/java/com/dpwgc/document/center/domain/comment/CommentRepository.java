package com.dpwgc.document.center.domain.comment;

public interface CommentRepository {

    String createComment(Comment comment);

    Boolean updateComment(Comment comment);
}
