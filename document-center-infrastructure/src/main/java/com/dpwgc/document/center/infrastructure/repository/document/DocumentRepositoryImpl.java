package com.dpwgc.document.center.infrastructure.repository.document;

import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.domain.document.DocumentRepository;
import com.dpwgc.document.center.infrastructure.assembler.DocumentPOAssembler;
import com.dpwgc.document.center.infrastructure.assembler.HitToDocumentPOAssembler;
import com.dpwgc.document.center.infrastructure.component.ESClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    @Resource
    ESClient esClient;

    @Resource
    HitToDocumentPOAssembler hitToDocumentPOAssembler;

    @Value("${elasticsearch.indexName}")
    private String indexName;

    @Override
    public String createDocument(Document document) {
        return esClient.insertDocument(indexName,document);
    }

    @Override
    public Document queryDocumentById(String id) {
        return DocumentPOAssembler.INSTANCE.assembleDocument(hitToDocumentPOAssembler.assemblerDocumentPO(esClient.searchDocumentById(indexName,id).get(0)));
    }

    @Override
    public Boolean updateDocument(Document document) {
        return esClient.updateDocument(indexName,document.getId(),document);
    }

    @Override
    public Boolean deleteDocument(String id) {
        return false;
    }
}
