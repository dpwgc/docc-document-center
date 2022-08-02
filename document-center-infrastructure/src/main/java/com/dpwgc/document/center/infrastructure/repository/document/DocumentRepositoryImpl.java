package com.dpwgc.document.center.infrastructure.repository.document;

import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.domain.document.DocumentRepository;
import com.dpwgc.document.center.infrastructure.assembler.DocumentPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    @Resource
    DocumentMapper documentMapper;

    @Override
    public String createDocument(Document document) {
        return documentMapper.insertDocument(DocumentPOAssembler.INSTANCE.assembleDocumentPO(document));
    }

    @Override
    public Document queryDocumentById(String id) {
        return DocumentPOAssembler.INSTANCE.assembleDocument(documentMapper.queryDocumentById(id));
    }

    @Override
    public Boolean updateDocument(Document document) {

        DocumentPO documentPO = DocumentPOAssembler.INSTANCE.assembleDocumentPO(document);
        return documentMapper.updateDocument(documentPO);
    }

    @Override
    public Boolean deleteDocument(Document document) {

        DocumentPO documentPO = DocumentPOAssembler.INSTANCE.assembleDocumentPO(document);
        return documentMapper.deleteDocument(documentPO);
    }
}
