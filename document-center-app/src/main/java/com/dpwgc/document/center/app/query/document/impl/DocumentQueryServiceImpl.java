package com.dpwgc.document.center.app.query.document.impl;

import com.dpwgc.document.center.app.assembler.DocumentAssembler;
import com.dpwgc.document.center.app.query.document.DocumentQueryService;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.document.AggregationQuery;
import com.dpwgc.document.center.sdk.model.document.AggregationDTO;
import com.dpwgc.document.center.sdk.model.document.DocumentQuery;
import com.dpwgc.document.center.sdk.model.document.DocumentDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentQueryServiceImpl implements DocumentQueryService {

    @Resource
    DocumentMapper documentMapper;

    /**
     * 根据ES主键id查询文档
     * @param id ES主键id
     * List<DocumentDTO>
     */
    @Override
    public DocumentDTO queryDocumentByESId(String id) throws IOException {
        DocumentPO documentPO = documentMapper.queryDocumentByESId(id);
        return DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO);
    }

    /**
     * 根据ES主键id集合查询文档
     * @param idList ES主键id集合
     * List<DocumentDTO>
     */
    @Override
    public List<DocumentDTO> queryDocumentByESIdList(List<String> idList) throws IOException {
        List<DocumentDTO> documentDTOList = new ArrayList<>();
        for (String id : idList) {
            DocumentPO documentPO = documentMapper.queryDocumentByESId(id);
            documentDTOList.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return documentDTOList;
    }

    /**
     * 文档检索
     * @return PageBase<List<DocumentDTO>>
     */
    @Override
    public PageBase<List<DocumentDTO>> searchDocument(DocumentQuery documentQuery) throws IOException {
        PageBase<List<DocumentPO>> pageBase = documentMapper.searchDocument(documentQuery);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }

    /**
     * 文档数据聚合统计
     * @return DocumentAggregationsDTO
     */
    @Override
    public AggregationDTO aggregationDocument(AggregationQuery aggregationQuery) throws IOException {
        return documentMapper.aggregationDocument(aggregationQuery);
    }
}
