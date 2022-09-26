package com.dpwgc.document.center.infrastructure.repository.tag;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.domain.tag.Tag;
import com.dpwgc.document.center.domain.tag.TagRepository;
import com.dpwgc.document.center.infrastructure.assembler.TagPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.tag.entity.TagPO;
import com.dpwgc.document.center.infrastructure.dal.tag.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class TagRepositoryImpl implements TagRepository {

    @Value("${optimistic.update.retry}")
    private int retry;

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
    public Boolean updateTag(Tag tag) {

        QueryWrapper<TagPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",tag.getAppId());
        queryWrapper.eq("tag_name",tag.getTagName());

        // 判断是否存在 + 版本号获取
        queryWrapper.select("version");
        TagPO po = tagMapper.selectOne(queryWrapper);
        if (po == null) {
            return false;
        }

        TagPO tagPO = TagPOAssembler.INSTANCE.assembleTagPO(tag);
        //带上版本号（乐观锁更新）
        tagPO.setVersion(po.getVersion());
        //更新时间
        tagPO.setUpdateTime(System.currentTimeMillis());

        for (int i=0;i<retry;i++) {
            //如果成功
            if (tagMapper.update(tagPO, queryWrapper) > 0) {
                return true;
            }
            //如果失败，重新获取版本，再次更新
            po = tagMapper.selectOne(queryWrapper);
            tagPO.setVersion(po.getVersion());
        }
        return false;
    }
}
