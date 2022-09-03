package com.dpwgc.document.center.app.assembler;

import com.dpwgc.document.center.domain.tag.Tag;
import com.dpwgc.document.center.infrastructure.dal.tag.entity.TagPO;
import com.dpwgc.document.center.sdk.model.tag.TagDTO;
import com.dpwgc.document.center.sdk.model.tag.UpdateTagCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagAssembler {

    TagAssembler INSTANCE = Mappers.getMapper(TagAssembler.class);

    TagDTO assembleTagDTO(Tag tag);

    Tag assembleTagFromUpdate(UpdateTagCommand updateTagCommand);

    TagDTO assembleTagDTO(TagPO tagPO);
}
