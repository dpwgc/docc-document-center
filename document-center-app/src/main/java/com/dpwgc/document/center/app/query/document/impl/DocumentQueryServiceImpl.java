package com.dpwgc.document.center.app.query.document.impl;

import com.dpwgc.document.center.app.assembler.DocumentAssembler;
import com.dpwgc.document.center.app.query.document.DocumentQueryService;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.common.DocumentQueryCommon;
import com.dpwgc.document.center.sdk.model.document.DocumentDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentQueryServiceImpl implements DocumentQueryService {

    @Resource
    DocumentMapper documentMapper;

    /**
     * 根据ES主键id集合查询文档
     * @param idList ES主键id集合
     * List<DocumentDTO>
     */
    @Override
    public List<DocumentDTO> queryDocumentByIdList(List<String> idList) {
        List<DocumentDTO> documentDTOList = new ArrayList<>();
        for (String id : idList) {
            DocumentPO documentPO = documentMapper.queryDocumentById(id);
            documentDTOList.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return documentDTOList;
    }

    /**
     * 查询应用内的所有文档列表
     * @return List<Hit<Object>>
     */
    @Override
    public PageBase<List<DocumentDTO>> queryDocument(DocumentQueryCommon documentQueryCommon) {
        PageBase<List<DocumentPO>> pageBase = documentMapper.queryDocument(documentQueryCommon);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }

    /**
     * 根据关键词检索应用内的所有文档
     * @param keyword 关键词
     * @return PageBase<List<DocumentDTO>>
     */
    @Override
    public PageBase<List<DocumentDTO>> queryDocumentByKeyword(String keyword, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<DocumentPO>> pageBase = documentMapper.queryDocumentByKeyword(keyword, documentQueryCommon);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }

    /**
     * 根据分类id检索文档
     * @param categoryId 分类id
     * @return PageBase<List<DocumentDTO>>
     */
    @Override
    public PageBase<List<DocumentDTO>> queryDocumentByCategoryId(String categoryId, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<DocumentPO>> pageBase = documentMapper.queryDocumentByCategoryId(categoryId, documentQueryCommon);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }

    /**
     * 根据分类id与关键词检索文档
     * @param categoryId 分类id
     * @param keyword 关键词
     * @return PageBase<List<DocumentDTO>>
     */
    @Override
    public PageBase<List<DocumentDTO>> queryDocumentByCategoryIdAndKeyword(String categoryId, String keyword, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<DocumentPO>> pageBase = documentMapper.queryDocumentByCategoryIdAndKeyword(categoryId, keyword, documentQueryCommon);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }

    /**
     * 根据分类id与文档类型type检索文档
     * @param categoryId 分类id
     * @param type 文档类型
     * @return PageBase<List<DocumentDTO>>
     */
    @Override
    public PageBase<List<DocumentDTO>> queryDocumentByCategoryIdAndType(String categoryId, Integer type, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<DocumentPO>> pageBase = documentMapper.queryDocumentByCategoryIdAndType(categoryId, type, documentQueryCommon);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }

    /**
     * 根据作者id检索文档
     * @param authorId 作者id
     * @return PageBase<List<DocumentDTO>>
     */
    @Override
    public PageBase<List<DocumentDTO>> queryDocumentByAuthorId(String authorId, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<DocumentPO>> pageBase = documentMapper.queryDocumentByAuthorId(authorId, documentQueryCommon);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }

    /**
     * 根据作者id与关键词检索文档
     * @param authorId 作者id
     * @param keyword 关键词
     * @return PageBase<List<DocumentDTO>>
     */
    @Override
    public PageBase<List<DocumentDTO>> queryDocumentByAuthorIdAndKeyword(String authorId, String keyword, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<DocumentPO>> pageBase = documentMapper.queryDocumentByAuthorIdAndKeyword(authorId, keyword, documentQueryCommon);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }

    /**
     * 根据标签检索文档
     * @param tags 标签
     * @return PageBase<List<DocumentDTO>>
     */
    @Override
    public PageBase<List<DocumentDTO>> queryDocumentByTags(String tags, DocumentQueryCommon documentQueryCommon) {
        PageBase<List<DocumentPO>> pageBase = documentMapper.queryDocumentByTags(tags, documentQueryCommon);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }
}
