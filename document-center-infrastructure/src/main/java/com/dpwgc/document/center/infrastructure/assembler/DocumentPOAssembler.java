package com.dpwgc.document.center.infrastructure.assembler;

import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import org.mapstruct.factory.Mappers;

public interface DocumentPOAssembler {

    DocumentPOAssembler INSTANCE = Mappers.getMapper(DocumentPOAssembler.class);

    Document assembleDocument(DocumentPO documentPO);

    DocumentPO assembleDocumentPO(Document document);
}
