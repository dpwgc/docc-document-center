package com.dpwgc.document.center.domain.tag;

public class TagFactory {

    public Tag create(String appId, String tagName) {
        return new Tag().create(appId, tagName);
    }
}
