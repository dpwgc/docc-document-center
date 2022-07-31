package com.dpwgc.document.center.infrastructure.dal.document.mapper;

import co.elastic.clients.elasticsearch._types.SortOrder;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.sdk.base.PageBase;

import java.util.List;

public interface DocumentMapper {

    String createDocument(DocumentPO documentPO);

    DocumentPO queryDocumentById(String id);

    Boolean updateDocument(DocumentPO documentPO);

    Boolean deleteDocument(DocumentPO documentPO);

    /**
     * 根据关键词检索文档
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByKeyword(String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据分类id检索文档
     * @param categoryId 分类id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByCategoryId(String categoryId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据分类id与关键词检索文档
     * @param categoryId 分类id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByCategoryIdAndKeyword(String categoryId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据分类id与文档类型type检索文档
     * @param categoryId 分类id
     * @param type 文档类型
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByCategoryIdAndType(String categoryId, Integer type, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据作者id检索文档
     * @param authorId 作者id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByAuthorId(String authorId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据作者id与关键词检索文档
     * @param authorId 作者id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByAuthorIdAndKeyword(String authorId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据标签检索文档
     * @param tags 标签
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return PageBase<List<DocumentPO>>
     */
    PageBase<List<DocumentPO>> queryDocumentByTags(String tags, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);
}
