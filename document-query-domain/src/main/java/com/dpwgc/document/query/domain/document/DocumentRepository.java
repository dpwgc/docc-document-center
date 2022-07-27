package com.dpwgc.document.query.domain.document;

public interface DocumentRepository {

    String createDocument(Document document);

    Boolean updateDocument(Document document);

    Boolean deleteDocument(String id);
}
