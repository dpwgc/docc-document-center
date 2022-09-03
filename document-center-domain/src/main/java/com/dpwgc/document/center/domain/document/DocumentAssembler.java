package com.dpwgc.document.center.domain.document;

import com.dpwgc.document.center.sdk.model.document.UpdateDocumentCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentAssembler {

    DocumentAssembler INSTANCE = Mappers.getMapper(DocumentAssembler.class);

    Document assembleDocumentFromUpdate(UpdateDocumentCommand updateDocumentCommand);
}
