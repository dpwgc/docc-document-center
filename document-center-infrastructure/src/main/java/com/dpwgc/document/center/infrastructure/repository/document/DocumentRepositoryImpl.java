package com.dpwgc.document.center.infrastructure.repository.document;

import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.domain.document.DocumentRepository;
import com.dpwgc.document.center.infrastructure.assembler.DocumentPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.io.IOException;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    @Resource
    DocumentMapper documentMapper;

    @Override
    public String createDocument(Document document) throws IOException {
        return documentMapper.insertDocument(DocumentPOAssembler.INSTANCE.assembleDocumentPO(document));
    }

    @Override
    public Boolean updateDocument(Document document) throws IOException {

        DocumentPO documentPO = DocumentPOAssembler.INSTANCE.assembleDocumentPO(document);
        return documentMapper.updateDocument(documentPO);
    }
}
