package com.dpwgc.document.center.infrastructure.assembler;

import com.dpwgc.document.center.domain.tag.Tag;
import com.dpwgc.document.center.infrastructure.dal.tag.entity.TagPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagPOAssembler {

    TagPOAssembler INSTANCE = Mappers.getMapper(TagPOAssembler.class);

    Tag assembleTag(TagPO tagPO);

    TagPO assembleTagPO(Tag tag);
}
