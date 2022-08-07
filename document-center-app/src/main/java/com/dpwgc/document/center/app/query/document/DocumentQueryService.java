package com.dpwgc.document.center.app.query.document;

import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.common.DocumentQueryCommon;
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
     * @return List<Hit<Object>>
     */
    PageBase<List<DocumentDTO>> queryDocument(DocumentQueryCommon documentQueryCommon);

    /**
     * 根据关键词检索应用内的所有文档
     * @param keyword 关键词
     * @return List<DocumentDTO>
     */
    PageBase<List<DocumentDTO>> queryDocumentByKeyword(String keyword, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据分类id检索文档
     * @param categoryId 分类id
     * @return List<DocumentDTO>
     */
    PageBase<List<DocumentDTO>> queryDocumentByCategoryId(String categoryId, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据分类id与关键词检索文档
     * @param categoryId 分类id
     * @param keyword 关键词
     * @return List<DocumentDTO>
     */
    PageBase<List<DocumentDTO>> queryDocumentByCategoryIdAndKeyword(String categoryId, String keyword, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据分类id与文档类型type检索文档
     * @param categoryId 分类id
     * @param type 文档类型
     * @return List<DocumentDTO>
     */
    PageBase<List<DocumentDTO>> queryDocumentByCategoryIdAndType(String categoryId, Integer type, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据作者id检索文档
     * @param authorId 作者id
     * @return List<DocumentDTO>
     */
    PageBase<List<DocumentDTO>> queryDocumentByAuthorId(String authorId, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据作者id与关键词检索文档
     * @param authorId 作者id
     * @param keyword 关键词
     * @return List<DocumentDTO>
     */
    PageBase<List<DocumentDTO>> queryDocumentByAuthorIdAndKeyword(String authorId, String keyword, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据标签检索文档
     * @param tags 标签
     * @return List<DocumentDTO>
     */
    PageBase<List<DocumentDTO>> queryDocumentByTags(String tags, DocumentQueryCommon documentQueryCommon);
}
