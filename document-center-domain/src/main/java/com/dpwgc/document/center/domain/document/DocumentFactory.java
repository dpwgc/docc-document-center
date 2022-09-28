package com.dpwgc.document.center.domain.document;

import java.util.Set;

public class DocumentFactory {

    public Document create(String appId, String categoryId, Set<String> authorId, String documentId, String title, String content, Set<String> tag, String summary, String extra, String remarks, Integer authLevel, Long score, Set<Integer> filter, Set<Integer> attr, Integer type) {
        return new Document().create(appId, categoryId, authorId, documentId, title, content, tag, summary, extra, remarks, authLevel, score, filter, attr, type);
    }
}
