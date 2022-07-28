package com.dpwgc.document.center.infrastructure.repository.document;

import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.domain.document.DocumentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    @Override
    public String createDocument(Document document) {
        return null;
    }

    @Override
    public Document queryDocumentById(String id) {
        return null;
    }

    @Override
    public Boolean updateDocument(Document document) {
        return false;
    }

    @Override
    public Boolean deleteDocument(String id) {
        return false;
    }
}
