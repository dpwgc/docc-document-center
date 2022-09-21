package com.dpwgc.document.center.domain.comment.sub;

public class SubCommentFactory {

    public SubComment create(String appId, String documentId, String commentId ,String subCommentId, String authorId, String replyTo, String content, String extra, Integer attr, Integer type) {
        return new SubComment().create(appId, documentId, commentId, subCommentId, authorId, replyTo, content, extra, attr, type);
    }
}
