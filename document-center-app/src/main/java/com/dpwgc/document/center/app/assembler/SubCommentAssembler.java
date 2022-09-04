package com.dpwgc.document.center.app.assembler;

import com.dpwgc.document.center.domain.comment.sub.SubComment;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.SubCommentPO;
import com.dpwgc.document.center.sdk.model.comment.sub.SubCommentDTO;
import com.dpwgc.document.center.sdk.model.comment.sub.UpdateSubCommentCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubCommentAssembler {

    SubCommentAssembler INSTANCE = Mappers.getMapper(SubCommentAssembler.class);

    SubCommentDTO assembleSubCommentDTO(SubComment subComment);

    SubComment assembleSubCommentFromUpdate(UpdateSubCommentCommand updateSubCommentCommand);

    SubCommentDTO assembleSubCommentDTO(SubCommentPO subCommentPO);
}
