package com.dpwgc.document.query.infrastructure.dal.document.mapper.impl;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.document.query.infrastructure.assembler.HitToDocumentPOAssembler;
import com.dpwgc.document.query.infrastructure.component.ESClient;
import com.dpwgc.document.query.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.query.infrastructure.dal.document.mapper.DocumentMapper;
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
    public List<DocumentPO> searchDocumentByKeyword(String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
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
    public List<DocumentPO> searchDocumentByCategoryId(String categoryId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByCategoryId(indexName,categoryId,authLevel,sortField,sortOrder,pageIndex,pageSize);
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
    public List<DocumentPO> searchDocumentByCategoryIdAndType(String categoryId, Integer type, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByCategoryIdAndType(indexName,categoryId,type,authLevel,sortField,sortOrder,pageIndex,pageSize);
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
    public List<DocumentPO> searchDocumentByTags(String tags, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<Hit<Object>> hits = esClient.searchDocumentByTags(indexName,tags,authLevel,sortField,sortOrder,pageIndex,pageSize);
        List<DocumentPO> documentPOS = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            documentPOS.add(hitToDocumentPOAssembler.assemblerDocumentPO(hit));
        }
        return documentPOS;
    }
}
