package com.dpwgc.document.center.app.assembler;

import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.sdk.model.document.DocumentDTO;
import org.mapstruct.factory.Mappers;

public interface DocumentAssembler {

    DocumentAssembler INSTANCE = Mappers.getMapper(DocumentAssembler.class);

    DocumentDTO assembleDocumentDTO(Document document);

    DocumentDTO assembleDocumentDTO(DocumentPO documentPO);
}
