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
     * 查询应用内的所有文档列表
     * @return List<Hit<Object>>
     */
    PageBase<List<DocumentPO>> queryDocument(DocumentQueryCommon documentQueryCommon);

    /**
     * 根据关键词检索应用内的所有文档
     * @param keyword 关键词
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByKeyword(String keyword, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据分类id检索文档
     * @param categoryId 分类id
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByCategoryId(String categoryId, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据分类id与关键词检索文档
     * @param categoryId 分类id
     * @param keyword 关键词
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByCategoryIdAndKeyword(String categoryId, String keyword, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据分类id与文档类型type检索文档
     * @param categoryId 分类id
     * @param type 文档类型
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByCategoryIdAndType(String categoryId, Integer type, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据作者id检索文档
     * @param authorId 作者id
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByAuthorId(String authorId, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据作者id与关键词检索文档
     * @param authorId 作者id
     * @param keyword 关键词
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByAuthorIdAndKeyword(String authorId, String keyword, DocumentQueryCommon documentQueryCommon);

    /**
     * 根据标签检索文档
     * @param tag 标签
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByTag(String tag, DocumentQueryCommon documentQueryCommon);
}
