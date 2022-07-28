package com.dpwgc.document.center.infrastructure.assembler;

import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentPOAssembler {

    DocumentPOAssembler INSTANCE = Mappers.getMapper(DocumentPOAssembler.class);

    Document assembleDocument(DocumentPO documentPO);

    DocumentPO assembleDocumentPO(Document document);
}
