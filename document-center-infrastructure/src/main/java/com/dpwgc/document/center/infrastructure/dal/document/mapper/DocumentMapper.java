package com.dpwgc.document.center.infrastructure.dal.document.mapper;

import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.document.AggregationsQuery;
import com.dpwgc.document.center.sdk.model.document.AggregationsDTO;
import com.dpwgc.document.center.sdk.model.document.DocumentQuery;

import java.io.IOException;
import java.util.List;

public interface DocumentMapper {

    String insertDocument(DocumentPO documentPO) throws IOException;

    DocumentPO queryDocumentById(String id) throws IOException;

    Boolean updateDocument(DocumentPO documentPO) throws IOException;

    /**
     * 文档检索
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> searchDocument(DocumentQuery documentQuery) throws IOException;

    /**
     * 文档数据聚合统计
     * @return DocumentAggregationsDTO
     */
    AggregationsDTO aggregationsDocument(AggregationsQuery aggregationsQuery) throws IOException;
}
