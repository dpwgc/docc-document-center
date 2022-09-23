package com.dpwgc.document.center.domain.document;

import java.util.List;

public class DocumentFactory {

    public Document create(String appId, String categoryId, String authorId, String documentId, String title, String content, List<String> tags, String summary, String extra, String remarks, Integer authLevel, Long score, Integer filter, Integer attr, Integer type) {
        return new Document().create(appId, categoryId, authorId, documentId, title, content, tags, summary, extra, remarks, authLevel, score, filter, attr, type);
    }
}
