package com.dpwgc.document.center.app.assembler;

import com.dpwgc.document.center.domain.comment.Comment;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.CommentPO;
import com.dpwgc.document.center.sdk.model.comment.CommentDTO;
import com.dpwgc.document.center.sdk.model.comment.UpdateCommentCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentAssembler {

    CommentAssembler INSTANCE = Mappers.getMapper(CommentAssembler.class);

    CommentDTO assembleCommentDTO(Comment comment);

    Comment assembleCommentFromUpdate(UpdateCommentCommand updateCommentCommand);

    CommentDTO assembleCommentDTO(CommentPO commentPO);
}
