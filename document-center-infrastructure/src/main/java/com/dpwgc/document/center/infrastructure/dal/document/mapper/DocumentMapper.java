package com.dpwgc.document.center.infrastructure.dal.document.mapper;

import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.common.DocumentQueryCommon;

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
    PageBase<List<DocumentPO>> searchDocument(DocumentQueryCommon documentQueryCommon);

    /**
     * 文档数据聚合统计
     * @return List<Hit<Object>>
     */
    PageBase<List<DocumentPO>> aggregationsDocument(DocumentQueryCommon documentQueryCommon);
}
