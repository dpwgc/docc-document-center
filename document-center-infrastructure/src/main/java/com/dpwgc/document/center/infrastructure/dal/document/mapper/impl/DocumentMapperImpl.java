package com.dpwgc.document.center.infrastructure.dal.document.mapper.impl;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.document.center.infrastructure.assembler.AggregationsMapToObjectAssembler;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.assembler.HitToDocumentPOAssembler;
import com.dpwgc.document.center.infrastructure.component.ESClient;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.document.AggregationsDocumentQuery;
import com.dpwgc.document.center.sdk.model.document.DocumentAggregationsDTO;
import com.dpwgc.document.center.sdk.model.document.SearchDocumentQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class DocumentMapperImpl implements DocumentMapper {

    @Resource
    ESClient esClient;

    @Resource
    HitToDocumentPOAssembler hitToDocumentPOAssembler;

    @Resource
    AggregationsMapToObjectAssembler aggregationsMapToObjectAssembler;

    @Value("${elasticsearch.indexName}")
    private String indexName;

    @Override
    public String insertDocument(DocumentPO documentPO) {
        return esClient.insertDocument(indexName,documentPO);
    }

    @Override
    public DocumentPO queryDocumentById(String id) {
        return hitToDocumentPOAssembler.assemblerDocumentPO(esClient.searchDocumentById(indexName,id).get(0));
    }

    @Override
    public Boolean updateDocument(DocumentPO documentPO) {
        return esClient.updateDocument(indexName,documentPO.getId(),documentPO);
    }

    /**
     * 文档检索
     * @return PageBase<List<DocumentPO>>
     */
    @Override
    public PageBase<List<DocumentPO>> searchDocument(SearchDocumentQuery searchDocumentQuery) {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchDocument(indexName, searchDocumentQuery);
        return PageBase.getPageBase(pageBase.getTotal(), hitToDocumentPOAssembler.assemblerDocumentPOList(pageBase.getList()));
    }
    /**
     * 文档数据聚合统计
     * @return DocumentAggregationsDTO
     */
    @Override
    public DocumentAggregationsDTO aggregationsDocument(AggregationsDocumentQuery aggregationsDocumentQuery) {
        return aggregationsMapToObjectAssembler.assemblerAggregations(esClient.aggregationsDocument(indexName, aggregationsDocumentQuery));
    }
}
