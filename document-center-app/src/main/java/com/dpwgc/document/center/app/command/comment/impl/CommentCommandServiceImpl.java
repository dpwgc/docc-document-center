package com.dpwgc.document.center.app.command.comment.impl;

import com.dpwgc.document.center.app.assembler.CommentAssembler;
import com.dpwgc.document.center.app.assembler.SubCommentAssembler;
import com.dpwgc.document.center.app.command.comment.CommentCommandService;
import com.dpwgc.document.center.domain.comment.Comment;
import com.dpwgc.document.center.domain.comment.CommentFactory;
import com.dpwgc.document.center.domain.comment.CommentRepository;
import com.dpwgc.document.center.domain.comment.sub.SubComment;
import com.dpwgc.document.center.domain.comment.sub.SubCommentFactory;
import com.dpwgc.document.center.domain.comment.sub.SubCommentRepository;
import com.dpwgc.document.center.infrastructure.util.IdGenUtil;
import com.dpwgc.document.center.sdk.model.comment.CreateCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.UpdateCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.sub.CreateSubCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.sub.UpdateSubCommentCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {

    @Resource
    CommentRepository commentRepository;

    @Resource
    SubCommentRepository subCommentRepository;

    @Resource
    IdGenUtil idGenUtil;

    @Override
    public String createComment(CreateCommentCommand createCategoryCommand) {
        CommentFactory commentFactory = new CommentFactory();
        Comment comment = commentFactory.create(
                createCategoryCommand.getAppId(),
                createCategoryCommand.getDocumentId(),
                idGenUtil.nextIdString(),
                createCategoryCommand.getAuthorId(),
                createCategoryCommand.getContent(),
                createCategoryCommand.getExtra(),
                createCategoryCommand.getAttr(),
                createCategoryCommand.getType()
        );
        return commentRepository.createComment(comment);
    }

    @Override
    public String createSubComment(CreateSubCommentCommand createSubCommentCommand) {
        SubCommentFactory subCommentFactory = new SubCommentFactory();
        SubComment subComment = subCommentFactory.create(
                createSubCommentCommand.getAppId(),
                createSubCommentCommand.getDocumentId(),
                createSubCommentCommand.getCommentId(),
                idGenUtil.nextIdString(),
                createSubCommentCommand.getAuthorId(),
                createSubCommentCommand.getReplyTo(),
                createSubCommentCommand.getContent(),
                createSubCommentCommand.getExtra()
        );
        return subCommentRepository.createSubComment(subComment);
    }

    @Override
    public Boolean updateComment(UpdateCommentCommand updateCommentCommand) {

        Comment comment = CommentAssembler.INSTANCE.assembleCommentFromUpdate(updateCommentCommand);

        comment.setUpdateTime(System.currentTimeMillis());

        return commentRepository.updateComment(comment);
    }

    @Override
    public Boolean updateSubComment(UpdateSubCommentCommand updateSubCommentCommand) {

        SubComment subComment = SubCommentAssembler.INSTANCE.assembleSubCommentFromUpdate(updateSubCommentCommand);

        subComment.setUpdateTime(System.currentTimeMillis());

        return subCommentRepository.updateSubComment(subComment);
    }
}
