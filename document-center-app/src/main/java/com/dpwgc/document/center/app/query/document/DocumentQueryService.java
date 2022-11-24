package com.dpwgc.document.center.app.query.document;

import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.document.AggregationQuery;
import com.dpwgc.document.center.sdk.model.document.AggregationDTO;
import com.dpwgc.document.center.sdk.model.document.DocumentQuery;
import com.dpwgc.document.center.sdk.model.document.DocumentDTO;

import java.io.IOException;
import java.util.List;

public interface DocumentQueryService {

    /**
     * 根据ES主键id查询文档
     * @param id ES主键id
     * @return DocumentDTO
     */
    DocumentDTO queryDocumentByESId(String id) throws IOException;

    /**
     * 根据ES主键id集合查询文档
     * @param idList ES主键id集合
     * @return List<DocumentDTO>
     */
    List<DocumentDTO> queryDocumentByESIdList(List<String> idList) throws IOException;

    /**
     * 文档检索
     */
    PageBase<List<DocumentDTO>> searchDocument(DocumentQuery documentQuery) throws IOException;

    /**
     * 文档数据聚合统计
     */
    AggregationDTO aggregationDocument(AggregationQuery aggregationQuery) throws IOException;

}
