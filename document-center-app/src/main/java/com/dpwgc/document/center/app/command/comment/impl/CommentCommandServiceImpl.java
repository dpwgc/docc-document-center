package com.dpwgc.document.center.app.command.comment.impl;

import com.dpwgc.document.center.app.command.comment.CommentCommandService;
import com.dpwgc.document.center.sdk.model.comment.CreateCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.UpdateCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.sub.CreateSubCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.sub.UpdateSubCommentCommand;
import org.springframework.stereotype.Service;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {

    @Override
    public String createComment(CreateCommentCommand createCategoryCommand) {
        return null;
    }

    @Override
    public String createSubComment(CreateSubCommentCommand createSubCommentCommand) {
        return null;
    }

    @Override
    public Boolean updateComment(UpdateCommentCommand updateCommentCommand) {
        return null;
    }

    @Override
    public Boolean updateSubComment(UpdateSubCommentCommand updateSubCommentCommand) {
        return null;
    }
}
