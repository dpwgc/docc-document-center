package com.dpwgc.document.center.domain.tag;

public interface TagRepository {

    Boolean createTag(Tag tag);

    Boolean updateTagNumber(Tag tag);

    Boolean deleteTag(Tag tag);
}
