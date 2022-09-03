package com.dpwgc.document.center.domain.document;

import java.io.IOException;

public interface DocumentRepository {

    String createDocument(Document document) throws IOException;

    Boolean updateDocument(Document document) throws IOException;
}
