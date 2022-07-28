package com.dpwgc.document.query.domain.document;

public class DocumentFactory {

    public Document create(String appId, String categoryId, String authorId, String documentId, String title, String content, String tags, String summary, Integer authLevel, Long score, Integer type, Long createTime, Long updateTime) {
        return new Document().create(appId, categoryId, authorId, documentId, title, content, tags, summary, authLevel, score, type, createTime, updateTime);
    }
}
