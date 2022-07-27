package com.dpwgc.document.query.domain.document;

public class DocumentFactory {

    public Document create(String appId, String categoryId, String authorId, String documentId, String title, String content, String tags, String summary, Integer authLevel, Integer type) {
        return new Document().create(appId, categoryId, authorId, documentId, title, content, tags, summary, authLevel, type);
    }
}
