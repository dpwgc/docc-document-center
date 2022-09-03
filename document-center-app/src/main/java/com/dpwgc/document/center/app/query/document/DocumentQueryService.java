package com.dpwgc.document.center.app.query.document;

import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.document.AggregationsQuery;
import com.dpwgc.document.center.sdk.model.document.AggregationsDTO;
import com.dpwgc.document.center.sdk.model.document.DocumentQuery;
import com.dpwgc.document.center.sdk.model.document.DocumentDTO;
import java.util.List;

public interface DocumentQueryService {

    /**
     * 根据ES主键id查询文档
     * @param id ES主键id
     * @return DocumentDTO
     */
    DocumentDTO queryDocumentById(String id);

    /**
     * 根据ES主键id集合查询文档
     * @param idList ES主键id集合
     * @return List<DocumentDTO>
     */
    List<DocumentDTO> queryDocumentByIdList(List<String> idList);

    /**
     * 文档检索
     */
    PageBase<List<DocumentDTO>> searchDocument(DocumentQuery documentQuery);

    /**
     * 文档数据聚合统计
     */
    AggregationsDTO aggregationsDocument(AggregationsQuery aggregationsQuery);

}
