package com.dpwgc.document.center.ui.controller;

import com.dpwgc.document.center.app.command.comment.CommentCommandService;
import com.dpwgc.document.center.app.query.comment.CommentQueryService;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.comment.CommentDTO;
import com.dpwgc.document.center.sdk.model.comment.CommentQuery;
import com.dpwgc.document.center.sdk.model.comment.CreateCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.UpdateCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.sub.CreateSubCommentCommand;
import com.dpwgc.document.center.sdk.model.comment.sub.SubCommentDTO;
import com.dpwgc.document.center.sdk.model.comment.sub.SubCommentQuery;
import com.dpwgc.document.center.sdk.model.comment.sub.UpdateSubCommentCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 文档评论相关接口
 */
@Api(value = "文档评论相关接口")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentCommandService commentCommandService;

    @Resource
    CommentQueryService commentQueryService;

    @ApiOperation(value = "创建评论")
    @PostMapping("/createComment")
    @MutationMapping
    public ResultDTO<String> createComment(@RequestBody @Validated CreateCommentCommand createCategoryCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(commentCommandService.createComment(createCategoryCommand));
    }

    @ApiOperation(value = "创建子评论")
    @PostMapping("/createSubComment")
    @MutationMapping
    public ResultDTO<String> createSubComment(@RequestBody @Validated CreateSubCommentCommand createSubCommentCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(commentCommandService.createSubComment(createSubCommentCommand));
    }

    @ApiOperation(value = "更新评论信息")
    @PostMapping("/updateComment")
    @MutationMapping
    public ResultDTO<Boolean> updateComment(@RequestBody @Validated UpdateCommentCommand updateCommentCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(commentCommandService.updateComment(updateCommentCommand));
    }

    @ApiOperation(value = "更新子评论信息")
    @PostMapping("/updateSubComment")
    @MutationMapping
    public ResultDTO<Boolean> updateSubComment(@RequestBody @Validated UpdateSubCommentCommand updateSubCommentCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(commentCommandService.updateSubComment(updateSubCommentCommand));
    }

    @ApiOperation(value = "获取文档评论")
    @GetMapping("/queryComment")
    @QueryMapping
    public ResultDTO<PageBase<List<CommentDTO>>> queryComment(@ModelAttribute CommentQuery commentQuery) {
        return ResultDTO.getSuccessResult(commentQueryService.queryComment(commentQuery));
    }

    @ApiOperation(value = "获取父评论下的子评论")
    @GetMapping("/querySubComment")
    @QueryMapping
    public ResultDTO<PageBase<List<SubCommentDTO>>> querySubComment(@ModelAttribute SubCommentQuery subCommentQuery) {
        return ResultDTO.getSuccessResult(commentQueryService.querySubComment(subCommentQuery));
    }
}
