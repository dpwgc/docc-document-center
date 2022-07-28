package com.dpwgc.document.center.infrastructure.dal.document.mapper.impl;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.infrastructure.assembler.DocumentPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.assembler.HitToDocumentPOAssembler;
import com.dpwgc.document.center.infrastructure.component.ESClient;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentMapperImpl implements DocumentMapper {

    @Resource
    ESClient esClient;

    @Resource
    HitToDocumentPOAssembler hitToDocumentPOAssembler;

    @Value("${elasticsearch.indexName}")
    private String indexName;

    @Override
    public String createDocument(DocumentPO documentPO) {
        return esClient.insertDocument(indexName,documentPO);
    }

    @Override
    public DocumentPO queryDocumentById(String id) {
        return hitToDocumentPOAssembler.assemblerDocumentPO(esClient.searchDocumentById(indexName,id).get(0));
    }

    @Override
    public Boolean updateDocument(DocumentPO documentPO) {
        return esClient.updateDocument(indexName,documentPO.getId(),documentPO);
    }

    @Override
    public Boolean deleteDocument(String id) {
        return esClient.deleteDocument(indexName,id);
    }

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
    @Override
    public List<DocumentPO> queryDocumentByKeyword(String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByKeyword(indexName,keyword,authLevel,sortField,sortOrder,pageIndex,pageSize);
        List<DocumentPO> documentPOS = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            documentPOS.add(hitToDocumentPOAssembler.assemblerDocumentPO(hit));
        }
        return documentPOS;
    }

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
    @Override
    public List<DocumentPO> queryDocumentByCategoryId(String categoryId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByCategoryId(indexName,categoryId,authLevel,sortField,sortOrder,pageIndex,pageSize);
        List<DocumentPO> documentPOS = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            documentPOS.add(hitToDocumentPOAssembler.assemblerDocumentPO(hit));
        }
        return documentPOS;
    }

    /**
     * 根据分类id与关键词检索文档
     * @param categoryId 分类id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    @Override
    public List<DocumentPO> queryDocumentByCategoryIdAndKeyword(String categoryId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByCategoryIdAndKeyword(indexName,categoryId,keyword,authLevel,sortField,sortOrder,pageIndex,pageSize);
        List<DocumentPO> documentPOS = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            documentPOS.add(hitToDocumentPOAssembler.assemblerDocumentPO(hit));
        }
        return documentPOS;
    }

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
    @Override
    public List<DocumentPO> queryDocumentByCategoryIdAndType(String categoryId, Integer type, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByCategoryIdAndType(indexName,categoryId,type,authLevel,sortField,sortOrder,pageIndex,pageSize);
        List<DocumentPO> documentPOS = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            documentPOS.add(hitToDocumentPOAssembler.assemblerDocumentPO(hit));
        }
        return documentPOS;
    }

    /**
     * 根据作者id检索文档
     * @param authorId 作者id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    @Override
    public List<DocumentPO> queryDocumentByAuthorId(String authorId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByAuthorId(indexName,authorId,authLevel,sortField,sortOrder,pageIndex,pageSize);
        List<DocumentPO> documentPOS = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            documentPOS.add(hitToDocumentPOAssembler.assemblerDocumentPO(hit));
        }
        return documentPOS;
    }

    /**
     * 根据作者id与关键词检索文档
     * @param authorId 作者id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return List<Hit<Object>>
     */
    @Override
    public List<DocumentPO> queryDocumentByAuthorIdAndKeyword(String authorId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByAuthorIdAndKeyword(indexName,authorId,keyword,authLevel,sortField,sortOrder,pageIndex,pageSize);
        List<DocumentPO> documentPOS = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            documentPOS.add(hitToDocumentPOAssembler.assemblerDocumentPO(hit));
        }
        return documentPOS;
    }

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
    @Override
    public List<DocumentPO> queryDocumentByTags(String tags, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByTags(indexName,tags,authLevel,sortField,sortOrder,pageIndex,pageSize);
        List<DocumentPO> documentPOS = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            documentPOS.add(hitToDocumentPOAssembler.assemblerDocumentPO(hit));
        }
        return documentPOS;
    }
}
