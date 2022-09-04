package com.dpwgc.document.center.infrastructure.dal.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.document.center.infrastructure.dal.comment.entity.SubCommentPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SubCommentMapper extends BaseMapper<SubCommentPO> {
}
