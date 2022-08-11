package com.dpwgc.document.center.app.query.document;

import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.document.AggregationsDocumentQuery;
import com.dpwgc.document.center.sdk.model.document.SearchDocumentQuery;
import com.dpwgc.document.center.sdk.model.document.DocumentDTO;
import java.util.List;

public interface DocumentQueryService {

    /**
     * 根据ES主键id集合查询文档
     * @param idList ES主键id集合
     * @return List<DocumentDTO>
     */
    List<DocumentDTO> queryDocumentByIdList(List<String> idList);

    /**
     * 查询应用内的所有文档列表
     */
    PageBase<List<DocumentDTO>> searchDocument(SearchDocumentQuery searchDocumentQuery);

    /**
     * 文档数据聚合统计
     */
    Object aggregationsDocument(AggregationsDocumentQuery aggregationsDocumentQuery);

}
