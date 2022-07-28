package com.dpwgc.document.center.app.query.document.impl;

import co.elastic.clients.elasticsearch._types.SortOrder;
import com.dpwgc.document.center.app.assembler.DocumentAssembler;
import com.dpwgc.document.center.app.query.document.DocumentQueryService;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
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
    public List<DocumentDTO> queryDocumentByKeyword(String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<DocumentPO> documentPOS = documentMapper.queryDocumentByKeyword(keyword, authLevel, sortField, sortOrder, pageIndex, pageSize);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : documentPOS) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return documentDTOS;
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
    public List<DocumentDTO> queryDocumentByCategoryId(String categoryId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<DocumentPO> documentPOS = documentMapper.queryDocumentByCategoryId(categoryId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : documentPOS) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return documentDTOS;
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
    public List<DocumentDTO> queryDocumentByCategoryIdAndKeyword(String categoryId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<DocumentPO> documentPOS = documentMapper.queryDocumentByCategoryIdAndKeyword(categoryId, keyword, authLevel, sortField, sortOrder, pageIndex, pageSize);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : documentPOS) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return documentDTOS;
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
    public List<DocumentDTO> queryDocumentByCategoryIdAndType(String categoryId, Integer type, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<DocumentPO> documentPOS = documentMapper.queryDocumentByCategoryIdAndType(categoryId, type, authLevel, sortField, sortOrder, pageIndex, pageSize);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : documentPOS) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return documentDTOS;
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
    public List<DocumentDTO> queryDocumentByAuthorId(String authorId, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<DocumentPO> documentPOS = documentMapper.queryDocumentByAuthorId(authorId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : documentPOS) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return documentDTOS;
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
    public List<DocumentDTO> queryDocumentByAuthorIdAndKeyword(String authorId, String keyword, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<DocumentPO> documentPOS = documentMapper.queryDocumentByAuthorIdAndKeyword(authorId, keyword, authLevel, sortField, sortOrder, pageIndex, pageSize);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : documentPOS) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return documentDTOS;
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
    public List<DocumentDTO> queryDocumentByTags(String tags, Integer authLevel, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        List<DocumentPO> documentPOS = documentMapper.queryDocumentByTags(tags, authLevel, sortField, sortOrder, pageIndex, pageSize);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : documentPOS) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return documentDTOS;
    }
}
