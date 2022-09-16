package com.dpwgc.document.center.app.query.comment.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.app.assembler.CommentAssembler;
import com.dpwgc.document.center.app.assembler.SubCommentAssembler;
import com.dpwgc.document.center.app.query.comment.CommentQueryService;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.CommentPO;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.SubCommentPO;
import com.dpwgc.document.center.infrastructure.dal.comment.mapper.CommentMapper;
import com.dpwgc.document.center.infrastructure.dal.comment.mapper.SubCommentMapper;
import com.dpwgc.document.center.sdk.base.Status;
import com.dpwgc.document.center.sdk.model.comment.CommentDTO;
import com.dpwgc.document.center.sdk.model.comment.CommentQuery;
import com.dpwgc.document.center.sdk.model.comment.sub.SubCommentDTO;
import com.dpwgc.document.center.sdk.model.comment.sub.SubCommentQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentQueryServiceImpl implements CommentQueryService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    SubCommentMapper subCommentMapper;

    @Override
    public List<CommentDTO> queryComment(CommentQuery commentQuery) {

        QueryWrapper<CommentPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",commentQuery.getAppId());
        queryWrapper.eq("document_id",commentQuery.getDocumentId());
        queryWrapper.eq("status", Status.NORMAL);

        if (commentQuery.getAuthorId() != null) {
            queryWrapper.eq("author_id",commentQuery.getAuthorId());
        }

        if (commentQuery.getAttr() != null) {
            queryWrapper.eq("attr",commentQuery.getAttr());
        }

        if (commentQuery.getType() != null) {
            queryWrapper.eq("type",commentQuery.getType());
        }

        if (commentQuery.getSortOrder().equals("desc")) {
            queryWrapper.orderByDesc(commentQuery.getSortField());
        } else {
            queryWrapper.orderByAsc(commentQuery.getSortField());
        }

        List<CommentPO> commentPOS = commentMapper.selectList(queryWrapper);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (CommentPO commentPO : commentPOS) {
            commentDTOS.add(CommentAssembler.INSTANCE.assembleCommentDTO(commentPO));
        }
        return commentDTOS;
    }

    @Override
    public List<SubCommentDTO> querySubComment(SubCommentQuery subCommentQuery) {

        QueryWrapper<SubCommentPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",subCommentQuery.getAppId());
        queryWrapper.eq("document_id",subCommentQuery.getDocumentId());
        queryWrapper.eq("comment_id",subCommentQuery.getCommentId());
        queryWrapper.eq("status", Status.NORMAL);

        if (subCommentQuery.getAuthorId() != null) {
            queryWrapper.eq("author_id",subCommentQuery.getAuthorId());
        }

        if (subCommentQuery.getReplyTo() != null) {
            queryWrapper.eq("reply_to",subCommentQuery.getReplyTo());
        }

        if (subCommentQuery.getSortOrder().equals("desc")) {
            queryWrapper.orderByDesc(subCommentQuery.getSortField());
        } else {
            queryWrapper.orderByAsc(subCommentQuery.getSortField());
        }

        List<SubCommentPO> subCommentPOS = subCommentMapper.selectList(queryWrapper);
        List<SubCommentDTO> subCommentDTOS = new ArrayList<>();
        for (SubCommentPO subCommentPO : subCommentPOS) {
            subCommentDTOS.add(SubCommentAssembler.INSTANCE.assembleSubCommentDTO(subCommentPO));
        }
        return subCommentDTOS;
    }
}
