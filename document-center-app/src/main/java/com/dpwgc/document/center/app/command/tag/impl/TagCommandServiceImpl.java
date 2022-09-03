package com.dpwgc.document.center.app.command.tag.impl;

import com.dpwgc.document.center.app.command.tag.TagCommandService;
import com.dpwgc.document.center.domain.tag.Tag;
import com.dpwgc.document.center.domain.tag.TagAssembler;
import com.dpwgc.document.center.domain.tag.TagRepository;
import com.dpwgc.document.center.sdk.model.tag.UpdateTagCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TagCommandServiceImpl implements TagCommandService {

    @Resource
    TagRepository tagRepository;

    @Override
    public Boolean updateTag(UpdateTagCommand updateTagCommand) {

        Tag tag = TagAssembler.INSTANCE.assembleTagFromUpdate(updateTagCommand);

        tag.setUpdateTime(System.currentTimeMillis());

        return tagRepository.updateTagNumber(tag);
    }
}
