package com.dpwgc.document.center.domain.document;

public interface DocumentRepository {

    String createDocument(Document document);

    Document queryDocumentById(String id);

    Boolean updateDocument(Document document);

    Boolean deleteDocument(String id);
}
