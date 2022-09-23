package com.dpwgc.document.center.infrastructure.repository.tag;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.domain.tag.Tag;
import com.dpwgc.document.center.domain.tag.TagRepository;
import com.dpwgc.document.center.infrastructure.assembler.TagPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.tag.entity.TagPO;
import com.dpwgc.document.center.infrastructure.dal.tag.mapper.TagMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class TagRepositoryImpl implements TagRepository {

    @Resource
    TagMapper tagMapper;

    @Override
    public Boolean createTag(Tag tag) {

        QueryWrapper<TagPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",tag.getAppId());
        queryWrapper.eq("tag_name",tag.getTagName());

        //检查标签是否已存在
        TagPO tagPO = tagMapper.selectOne(queryWrapper);

        //如果标签已存在
        if (tagPO != null) {
            //更新标签数量和时间
            tagPO.setNumber(tagPO.getNumber()+1);
            tagPO.setUpdateTime(System.currentTimeMillis());
            return tagMapper.update(tagPO ,queryWrapper) > 0;
        } else {
            //插入新标签
            return tagMapper.insert(TagPOAssembler.INSTANCE.assembleTagPO(tag)) > 0;
        }
    }

    @Override
    public Boolean updateTagNumber(Tag tag) {

        QueryWrapper<TagPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",tag.getAppId());
        queryWrapper.eq("tag_name",tag.getTagName());

        TagPO tagPO = tagMapper.selectOne(queryWrapper);

        tagPO.setNumber(tagPO.getNumber());

        return tagMapper.update(tagPO,queryWrapper) > 0;
    }

    @Override
    public Boolean deleteTag(Tag tag) {

        QueryWrapper<TagPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",tag.getAppId());
        queryWrapper.eq("tag_name",tag.getTagName());

        TagPO tagPO = tagMapper.selectOne(queryWrapper);

        tagPO.setStatus(0);

        return tagMapper.update(tagPO,queryWrapper) > 0;
    }
}
