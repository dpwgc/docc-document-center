package com.dpwgc.document.center.app.command.tag.impl;

import com.dpwgc.document.center.app.command.tag.TagCommandService;
import com.dpwgc.document.center.domain.tag.Tag;
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
        Tag tag = new Tag();
        tag.setAppId(updateTagCommand.getAppId());
        tag.setTagName(updateTagCommand.getTagName());
        tag.setNumber(updateTagCommand.getNumber());
        tag.setStatus(updateTagCommand.getStatus());

        return tagRepository.updateTag(tag);
    }
}
