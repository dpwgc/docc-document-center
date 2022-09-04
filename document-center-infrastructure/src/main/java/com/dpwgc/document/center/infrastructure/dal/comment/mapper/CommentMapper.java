package com.dpwgc.document.center.infrastructure.dal.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.CommentPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper extends BaseMapper<CommentPO> {
}
