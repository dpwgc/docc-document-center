package com.dpwgc.document.center.app.command.tag.impl;

import com.dpwgc.document.center.app.command.tag.TagCommandService;
import com.dpwgc.document.center.domain.tag.Tag;
import com.dpwgc.document.center.domain.tag.TagRepository;
import com.dpwgc.document.center.sdk.model.tag.DeleteTagCommand;
import com.dpwgc.document.center.sdk.model.tag.UpdateTagNumberCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TagCommandServiceImpl implements TagCommandService {

    @Resource
    TagRepository tagRepository;

    @Override
    public Boolean updateTagNumber(UpdateTagNumberCommand updateTagNumberCommand) {
        Tag tag = new Tag();
        tag.setAppId(updateTagNumberCommand.getAppId());
        tag.setTagName(updateTagNumberCommand.getTagName());
        tag.setNumber(updateTagNumberCommand.getNumber());

        return tagRepository.updateTagNumber(tag);
    }

    @Override
    public Boolean deleteTag(DeleteTagCommand deleteTagCommand) {
        Tag tag = new Tag();
        tag.setAppId(deleteTagCommand.getAppId());
        tag.setTagName(deleteTagCommand.getTagName());
        tag.setStatus(deleteTagCommand.getStatus());

        return tagRepository.deleteTag(tag);
    }
}
