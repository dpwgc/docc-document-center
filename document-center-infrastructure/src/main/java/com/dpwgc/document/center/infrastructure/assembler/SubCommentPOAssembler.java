package com.dpwgc.document.center.infrastructure.assembler;

import com.dpwgc.document.center.domain.comment.sub.SubComment;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.SubCommentPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubCommentPOAssembler {

    SubCommentPOAssembler INSTANCE = Mappers.getMapper(SubCommentPOAssembler.class);

    SubComment assembleSubComment(SubCommentPO subCommentPO);

    SubCommentPO assembleSubCommentPO(SubComment subComment);
}
