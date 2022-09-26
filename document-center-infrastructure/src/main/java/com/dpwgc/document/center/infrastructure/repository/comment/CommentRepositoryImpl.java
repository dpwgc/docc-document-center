package com.dpwgc.document.center.infrastructure.repository.comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.domain.comment.Comment;
import com.dpwgc.document.center.domain.comment.CommentRepository;
import com.dpwgc.document.center.infrastructure.assembler.CommentPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.CommentPO;
import com.dpwgc.document.center.infrastructure.dal.comment.mapper.CommentMapper;
import com.dpwgc.document.center.sdk.base.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Value("${optimistic.update.retry}")
    private int retry;

    @Resource
    CommentMapper commentMapper;

    public String createComment(Comment comment) {

        if (commentMapper.insert(CommentPOAssembler.INSTANCE.assembleCommentPO(comment))>0) {
            return comment.getCommentId();
        }
        return null;
    }

    public Boolean updateComment(Comment comment) {

        QueryWrapper<CommentPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",comment.getAppId());
        queryWrapper.eq("document_id",comment.getDocumentId());
        queryWrapper.eq("comment_id",comment.getCommentId());
        queryWrapper.eq("status", Status.NORMAL);

        // 判断是否存在 + 版本号获取
        queryWrapper.select("version");
        CommentPO po = commentMapper.selectOne(queryWrapper);
        if (po == null) {
            return false;
        }

        CommentPO commentPO = CommentPOAssembler.INSTANCE.assembleCommentPO(comment);
        //带上版本号（乐观锁更新）
        commentPO.setVersion(po.getVersion());
        //更新时间
        commentPO.setUpdateTime(System.currentTimeMillis());

        for (int i=0;i<retry;i++) {
            //如果成功
            if (commentMapper.update(commentPO, queryWrapper) > 0) {
                return true;
            }
            //如果失败，重新获取版本，再次更新
            po = commentMapper.selectOne(queryWrapper);
            commentPO.setVersion(po.getVersion());
        }
        return false;
    }
}
