package com.dpwgc.document.center.domain.document;

public class DocumentFactory {

    public Document create(String appId, String categoryId, String authorId, String documentId, String title, String content, String tags, String summary, Integer authLevel, Long score, Long love, Long like, Long read, Integer type) {
        return new Document().create(appId, categoryId, authorId, documentId, title, content, tags, summary, authLevel, score, love, like, read, type);
    }
}
