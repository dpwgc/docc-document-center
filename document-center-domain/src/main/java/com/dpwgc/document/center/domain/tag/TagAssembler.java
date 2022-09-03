package com.dpwgc.document.center.domain.tag;

import com.dpwgc.document.center.sdk.model.tag.UpdateTagCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagAssembler {

    TagAssembler INSTANCE = Mappers.getMapper(TagAssembler.class);

    Tag assembleTagFromUpdate(UpdateTagCommand updateTagCommand);
}
