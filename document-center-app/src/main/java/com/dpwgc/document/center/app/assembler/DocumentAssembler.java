package com.dpwgc.document.center.app.assembler;

import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.sdk.model.document.DocumentDTO;
import com.dpwgc.document.center.sdk.model.document.UpdateDocumentCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentAssembler {

    DocumentAssembler INSTANCE = Mappers.getMapper(DocumentAssembler.class);

    DocumentDTO assembleDocumentDTO(Document document);

    Document assembleDocumentFromUpdate(UpdateDocumentCommand updateDocumentCommand);

    DocumentDTO assembleDocumentDTO(DocumentPO documentPO);
}
