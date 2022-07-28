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
        return documentMapper.createDocument(DocumentPOAssembler.INSTANCE.assembleDocumentPO(document));
    }

    @Override
    public Document queryDocumentById(String id) {
        return DocumentPOAssembler.INSTANCE.assembleDocument(documentMapper.queryDocumentById(id));
    }

    @Override
    public Boolean updateDocument(Document document) {

        DocumentPO documentPO = DocumentPOAssembler.INSTANCE.assembleDocumentPO(document);

        //更新时间
        documentPO.setUpdateTime(System.currentTimeMillis());

        return documentMapper.updateDocument(documentPO);
    }

    @Override
    public Boolean deleteDocument(Document document) {

        DocumentPO documentPO = DocumentPOAssembler.INSTANCE.assembleDocumentPO(document);

        documentPO.setUpdateTime(System.currentTimeMillis());   //更新时间
        documentPO.setStatus(0);                                //设置状态为0

        return documentMapper.deleteDocument(documentPO);
    }
}
