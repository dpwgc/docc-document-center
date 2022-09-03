package com.dpwgc.document.center.app.command.comment;

import com.dpwgc.document.center.sdk.model.comment.CreateCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.UpdateCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.sub.CreateSubCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.sub.UpdateSubCommentCommand;

public interface CommentCommandService {

    String createComment(CreateCommentCommand createCategoryCommand);

    String createSubComment(CreateSubCommentCommand createSubCommentCommand);

    Boolean updateComment(UpdateCommentCommand updateCommentCommand);

    Boolean updateSubComment(UpdateSubCommentCommand updateSubCommentCommand);
}
