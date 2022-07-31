package com.dpwgc.document.center.infrastructure.dal.document.mapper.impl;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.assembler.HitToDocumentPOAssembler;
import com.dpwgc.document.center.infrastructure.component.ESClient;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.common.DocumentQueryCommon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
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
    public Boolean deleteDocument(DocumentPO documentPO) {
        return esClient.updateDocument(indexName,documentPO.getId(),documentPO);
    }

    /**
     * 根据关键词检索文档
     * @param keyword 关键词
     * @return PageBase<List<DocumentPO>>
     */
    @Override
    public PageBase<List<DocumentPO>> queryDocumentByKeyword(String keyword, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchDocumentByKeyword(indexName,keyword,documentQueryCommon);
        return PageBase.getPageBase(pageBase.getTotal(), hitToDocumentPOAssembler.assemblerDocumentPOList(pageBase.getList()));
    }

    /**
     * 根据分类id检索文档
     * @param categoryId 分类id
     * @return PageBase<List<DocumentPO>>
     */
    @Override
    public PageBase<List<DocumentPO>> queryDocumentByCategoryId(String categoryId, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchDocumentByCategoryId(indexName,categoryId,documentQueryCommon);
        return PageBase.getPageBase(pageBase.getTotal(), hitToDocumentPOAssembler.assemblerDocumentPOList(pageBase.getList()));
    }

    /**
     * 根据分类id与关键词检索文档
     * @param categoryId 分类id
     * @param keyword 关键词
     * @return PageBase<List<DocumentPO>>
     */
    @Override
    public PageBase<List<DocumentPO>> queryDocumentByCategoryIdAndKeyword(String categoryId, String keyword, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchDocumentByCategoryIdAndKeyword(indexName,categoryId,keyword,documentQueryCommon);
        return PageBase.getPageBase(pageBase.getTotal(), hitToDocumentPOAssembler.assemblerDocumentPOList(pageBase.getList()));
    }

    /**
     * 根据分类id与文档类型type检索文档
     * @param categoryId 分类id
     * @param type 文档类型
     * @return PageBase<List<DocumentPO>>
     */
    @Override
    public PageBase<List<DocumentPO>> queryDocumentByCategoryIdAndType(String categoryId, Integer type, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchDocumentByCategoryIdAndType(indexName,categoryId,type,documentQueryCommon);
        return PageBase.getPageBase(pageBase.getTotal(), hitToDocumentPOAssembler.assemblerDocumentPOList(pageBase.getList()));
    }

    /**
     * 根据作者id检索文档
     * @param authorId 作者id
     * @return PageBase<List<DocumentPO>>
     */
    @Override
    public PageBase<List<DocumentPO>> queryDocumentByAuthorId(String authorId, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchDocumentByAuthorId(indexName,authorId,documentQueryCommon);
        return PageBase.getPageBase(pageBase.getTotal(), hitToDocumentPOAssembler.assemblerDocumentPOList(pageBase.getList()));
    }

    /**
     * 根据作者id与关键词检索文档
     * @param authorId 作者id
     * @param keyword 关键词
     * @return PageBase<List<DocumentPO>>
     */
    @Override
    public PageBase<List<DocumentPO>> queryDocumentByAuthorIdAndKeyword(String authorId, String keyword, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchDocumentByAuthorIdAndKeyword(indexName,authorId,keyword,documentQueryCommon);
        return PageBase.getPageBase(pageBase.getTotal(), hitToDocumentPOAssembler.assemblerDocumentPOList(pageBase.getList()));
    }

    /**
     * 根据标签检索文档
     * @param tags 标签
     * @return PageBase<List<DocumentPO>>
     */
    @Override
    public PageBase<List<DocumentPO>> queryDocumentByTags(String tags, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<Hit<Object>>> pageBase = esClient.searchDocumentByTags(indexName,tags,documentQueryCommon);
        return PageBase.getPageBase(pageBase.getTotal(), hitToDocumentPOAssembler.assemblerDocumentPOList(pageBase.getList()));
    }
}
