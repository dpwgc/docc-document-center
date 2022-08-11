package com.dpwgc.document.center.infrastructure.dal.document.mapper;

import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.document.AggregationsDocumentQuery;
import com.dpwgc.document.center.sdk.model.document.SearchDocumentQuery;

import java.util.List;

public interface DocumentMapper {

    String insertDocument(DocumentPO documentPO);

    DocumentPO queryDocumentById(String id);

    Boolean updateDocument(DocumentPO documentPO);

    Boolean deleteDocument(DocumentPO documentPO);

    /**
     * 文档检索
     * @return List<Hit<Object>>
     */
    PageBase<List<DocumentPO>> searchDocument(SearchDocumentQuery searchDocumentQuery);

    /**
     * 文档数据聚合统计
     * @return List<Hit<Object>>
     */
    PageBase<List<DocumentPO>> aggregationsDocument(AggregationsDocumentQuery aggregationsDocumentQuery);
}
