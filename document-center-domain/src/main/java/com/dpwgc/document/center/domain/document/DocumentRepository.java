package com.dpwgc.document.center.domain.document;

public interface DocumentRepository {

    String createDocument(Document document);

    Boolean updateDocument(Document document);
}
