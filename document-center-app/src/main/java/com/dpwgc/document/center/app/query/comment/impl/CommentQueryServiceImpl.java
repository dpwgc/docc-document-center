package com.dpwgc.document.center.app.query.comment.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.document.center.app.assembler.CommentAssembler;
import com.dpwgc.document.center.app.assembler.SubCommentAssembler;
import com.dpwgc.document.center.app.query.comment.CommentQueryService;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.CommentPO;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.SubCommentPO;
import com.dpwgc.document.center.infrastructure.dal.comment.mapper.CommentMapper;
import com.dpwgc.document.center.infrastructure.dal.comment.mapper.SubCommentMapper;
import com.dpwgc.document.center.infrastructure.util.StringUtil;
import com.dpwgc.document.center.sdk.base.PageBase;
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
    public PageBase<List<CommentDTO>> queryComment(CommentQuery commentQuery) {

        PageBase<List<CommentDTO>> pageBase = new PageBase<>();

        Page<CommentPO> page = new Page<>(commentQuery.getPageIndex(), commentQuery.getPageSize());

        QueryWrapper<CommentPO> queryWrapper = new QueryWrapper<>();

        if (StringUtil.notEmpty(commentQuery.getAppId())) {
            queryWrapper.eq("app_id",commentQuery.getAppId());
        }
        if (StringUtil.notEmpty(commentQuery.getDocumentId())) {
            queryWrapper.eq("document_id",commentQuery.getDocumentId());
        }
        queryWrapper.eq("status", Status.NORMAL);

        if (StringUtil.notEmpty(commentQuery.getAuthorId())) {
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

        Page<CommentPO> commentPOS = commentMapper.selectPage(page, queryWrapper);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (CommentPO commentPO : commentPOS.getRecords()) {
            CommentDTO commentDTO = CommentAssembler.INSTANCE.assembleCommentDTO(commentPO);
            commentDTO.setSubCommentList(getSubCommentList(commentDTO.getAppId(),commentDTO.getDocumentId(),commentDTO.getCommentId()));
            commentDTOS.add(commentDTO);
        }

        pageBase.setTotal(commentPOS.getTotal());
        pageBase.setList(commentDTOS);

        return pageBase;
    }

    private PageBase<List<SubCommentDTO>> getSubCommentList(String appId,String documentId,String commentId) {
        SubCommentQuery subCommentQuery = new SubCommentQuery();
        subCommentQuery.setAppId(appId);
        subCommentQuery.setDocumentId(documentId);
        subCommentQuery.setCommentId(commentId);
        //按创建时间降序排序，每条评论展示三条子评论
        subCommentQuery.setSortField("create_time");
        subCommentQuery.setSortOrder("desc");
        subCommentQuery.setPageIndex(0);
        subCommentQuery.setPageSize(3);
        return querySubComment(subCommentQuery);
    }

    @Override
    public PageBase<List<SubCommentDTO>> querySubComment(SubCommentQuery subCommentQuery) {

        PageBase<List<SubCommentDTO>> pageBase = new PageBase<>();

        Page<SubCommentPO> page = new Page<>(subCommentQuery.getPageIndex(), subCommentQuery.getPageSize());

        QueryWrapper<SubCommentPO> queryWrapper = new QueryWrapper<>();

        if (StringUtil.notEmpty(subCommentQuery.getAppId())) {
            queryWrapper.eq("app_id",subCommentQuery.getAppId());
        }
        if (StringUtil.notEmpty(subCommentQuery.getDocumentId())) {
            queryWrapper.eq("document_id",subCommentQuery.getDocumentId());
        }
        if (StringUtil.notEmpty(subCommentQuery.getCommentId())) {
            queryWrapper.eq("comment_id",subCommentQuery.getCommentId());
        }
        queryWrapper.eq("status", Status.NORMAL);

        if (StringUtil.notEmpty(subCommentQuery.getAuthorId())) {
            queryWrapper.eq("author_id",subCommentQuery.getAuthorId());
        }

        if (StringUtil.notEmpty(subCommentQuery.getReplyTo())) {
            queryWrapper.eq("reply_to",subCommentQuery.getReplyTo());
        }

        if (subCommentQuery.getSortOrder().equals("desc")) {
            queryWrapper.orderByDesc(subCommentQuery.getSortField());
        } else {
            queryWrapper.orderByAsc(subCommentQuery.getSortField());
        }

        Page<SubCommentPO> subCommentPOS = subCommentMapper.selectPage(page, queryWrapper);
        List<SubCommentDTO> subCommentDTOS = new ArrayList<>();
        for (SubCommentPO subCommentPO : subCommentPOS.getRecords()) {
            subCommentDTOS.add(SubCommentAssembler.INSTANCE.assembleSubCommentDTO(subCommentPO));
        }

        pageBase.setTotal(subCommentPOS.getTotal());
        pageBase.setList(subCommentDTOS);

        return pageBase;
    }
}
