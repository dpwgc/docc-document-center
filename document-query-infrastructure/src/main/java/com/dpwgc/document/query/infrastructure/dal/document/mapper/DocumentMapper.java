package com.dpwgc.document.query.infrastructure.dal.document.mapper;

import co.elastic.clients.elasticsearch._types.SortOrder;
import com.dpwgc.document.query.infrastructure.dal.document.entity.DocumentPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DocumentMapper {

    /**
     * 根据关键词检索文档
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<DocumentPO>
     */
    List<DocumentPO> queryDocumentByKeyword(String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据分类id检索文档
     * @param categoryId 分类id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<DocumentPO>
     */
    List<DocumentPO> queryDocumentByCategoryId(String categoryId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据分类id与关键词检索文档
     * @param indexName 索引名称
     * @param categoryId 分类id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    List<DocumentPO> queryDocumentByCategoryIdAndKeyword(String indexName, String categoryId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据分类id与文档类型type检索文档
     * @param categoryId 分类id
     * @param type 文档类型
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<DocumentPO>
     */
    List<DocumentPO> queryDocumentByCategoryIdAndType(String categoryId, Integer type, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据作者id检索文档
     * @param indexName 索引名称
     * @param authorId 作者id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    List<DocumentPO> queryDocumentByAuthorId(String indexName, String authorId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据作者id与关键词检索文档
     * @param indexName 索引名称
     * @param authorId 作者id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    List<DocumentPO> queryDocumentByAuthorIdAndKeyword(String indexName, String authorId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);

    /**
     * 根据标签检索文档
     * @param tags 标签
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<DocumentPO>
     */
    List<DocumentPO> queryDocumentByTags(String tags, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize);
}
