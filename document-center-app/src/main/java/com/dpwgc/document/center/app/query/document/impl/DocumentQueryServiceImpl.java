package com.dpwgc.document.center.app.query.document.impl;

import com.dpwgc.document.center.app.assembler.DocumentAssembler;
import com.dpwgc.document.center.app.query.document.DocumentQueryService;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.dal.document.mapper.DocumentMapper;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.document.AggregationsDocumentQuery;
import com.dpwgc.document.center.sdk.model.document.SearchDocumentQuery;
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
     * 文档检索
     * @return List<Hit<Object>>
     */
    @Override
    public PageBase<List<DocumentDTO>> searchDocument(SearchDocumentQuery searchDocumentQuery) {
        PageBase<List<DocumentPO>> pageBase = documentMapper.searchDocument(searchDocumentQuery);
        List<DocumentDTO> documentDTOS = new ArrayList<>();
        for (DocumentPO documentPO : pageBase.getList()) {
            documentDTOS.add(DocumentAssembler.INSTANCE.assembleDocumentDTO(documentPO));
        }
        return PageBase.getPageBase(pageBase.getTotal(),documentDTOS);
    }

    /**
     * 文档数据聚合统计
     * @return List<Hit<Object>>
     */
    @Override
    public Object aggregationsDocument(AggregationsDocumentQuery aggregationsDocumentQuery) {
        return documentMapper.aggregationsDocument(aggregationsDocumentQuery);
    }
}
