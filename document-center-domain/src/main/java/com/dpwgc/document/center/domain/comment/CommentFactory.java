package com.dpwgc.document.center.domain.comment;

import com.dpwgc.document.center.domain.category.Category;

public class CommentFactory {

    public Comment create(String appId, String documentId, String commentId, String authorId, String content, String extra, Integer attr, Integer type) {
        return new Comment().create(appId, documentId, commentId, authorId, content, extra, attr, type);
    }
}
