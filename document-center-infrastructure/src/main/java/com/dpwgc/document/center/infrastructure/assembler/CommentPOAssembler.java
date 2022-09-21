package com.dpwgc.document.center.infrastructure.assembler;

import com.dpwgc.document.center.domain.comment.Comment;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.CommentPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentPOAssembler {

    CommentPOAssembler INSTANCE = Mappers.getMapper(CommentPOAssembler.class);

    Comment assembleComment(CommentPO commentPO);

    CommentPO assembleCommentPO(Comment comment);
}
