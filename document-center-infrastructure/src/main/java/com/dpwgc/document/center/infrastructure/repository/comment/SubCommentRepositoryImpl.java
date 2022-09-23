package com.dpwgc.document.center.infrastructure.repository.comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.domain.comment.sub.SubComment;
import com.dpwgc.document.center.domain.comment.sub.SubCommentRepository;
import com.dpwgc.document.center.infrastructure.assembler.SubCommentPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.SubCommentPO;
import com.dpwgc.document.center.infrastructure.dal.comment.mapper.SubCommentMapper;
import com.dpwgc.document.center.sdk.base.Status;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SubCommentRepositoryImpl implements SubCommentRepository {

    @Resource
    SubCommentMapper subCommentMapper;

    public String createSubComment(SubComment subComment) {
        if (subCommentMapper.insert(SubCommentPOAssembler.INSTANCE.assembleSubCommentPO(subComment))>0) {
            return subComment.getSubCommentId();
        }
        return null;
    }

    public Boolean updateSubComment(SubComment subComment) {

        QueryWrapper<SubCommentPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",subComment.getAppId());
        queryWrapper.eq("document_id",subComment.getDocumentId());
        queryWrapper.eq("comment_id",subComment.getCommentId());
        queryWrapper.eq("sub_comment_id",subComment.getSubCommentId());
        queryWrapper.eq("status", Status.NORMAL);

        SubCommentPO subCommentPO = SubCommentPOAssembler.INSTANCE.assembleSubCommentPO(subComment);
        subComment.setUpdateTime(System.currentTimeMillis());

        return subCommentMapper.update(subCommentPO, queryWrapper) > 0;
    }
}
