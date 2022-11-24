package com.dpwgc.document.center.infrastructure.dal.document.mapper.impl;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.document.center.infrastructure.assembler.AggregationMapToObjectAssembler;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.assembler.HitToDocumentPOAssembler;
import com.dpwgc.document.center.infrastructure.component.ESClient;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.document.AggregationQuery;
import com.dpwgc.document.center.sdk.model.document.AggregationDTO;
import com.dpwgc.document.center.sdk.model.document.DocumentQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class DocumentMapperImpl implements DocumentMapper {

    @Resource
    ESClient esClient;

    @Resource
    HitToDocumentPOAssembler hitToDocumentPOAssembler;

    @Resource
    AggregationMapToObjectAssembler aggregationMapToObjectAssembler;

    @Value("${elasticsearch.indexName}")
    private String indexName;

    @Override
    public String insertDocument(DocumentPO documentPO) throws IOException {
        return esClient.insertDocument(indexName,documentPO);
    }

    @Override
    public DocumentPO queryDocumentByESId(String id) throws IOException {
        List<Hit<Object>> list = esClient.searchDocumentByESId(indexName,id);
        if (list == null || list.size() == 0) {
            return null;
        }
        return hitToDocumentPOAssembler.assemblerDocumentPO(list.get(0));
    }

    @Override
    public Boolean updateDocument(DocumentPO documentPO) throws IOException {
        return esClient.updateDocument(indexName,documentPO);
    }

    /**
     * 文档检索
     * @return PageBase<List<DocumentPO>>
     */
    @Override
    public PageBase<List<DocumentPO>> searchDocument(DocumentQuery documentQuery) throws IOException {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchDocument(indexName, documentQuery);
        return PageBase.getPageBase(pageBase.getTotal(), hitToDocumentPOAssembler.assemblerDocumentPOList(pageBase.getList()));
    }
    /**
     * 文档数据聚合统计
     * @return DocumentAggregationsDTO
     */
    @Override
    public AggregationDTO aggregationDocument(AggregationQuery aggregationQuery) throws IOException {
        return aggregationMapToObjectAssembler.assemblerAggregation(esClient.aggregationDocument(indexName, aggregationQuery));
    }
}
