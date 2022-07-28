package com.dpwgc.document.center.infrastructure.assembler;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import org.springframework.stereotype.Component;

@Component
public class HitToDocumentPOAssembler {

    /**
     * elasticsearch的Hit对象转DocumentPO对象
     */
    public DocumentPO assemblerDocumentPO(Hit<Object> hit) {
        DocumentPO documentPO = new DocumentPO();
        documentPO.setId(hit.id());
        documentPO.setAppId(hit.fields().get("app_id").to(String.class));
        documentPO.setCategoryId(hit.fields().get("document_id").to(String.class));
        documentPO.setAuthorId(hit.fields().get("author_id").to(String.class));
        documentPO.setDocumentId(hit.fields().get("document_id").to(String.class));
        documentPO.setTitle(hit.fields().get("title").to(String.class));
        documentPO.setContent(hit.fields().get("content").to(String.class));
        documentPO.setTags(hit.fields().get("tags").to(String.class));
        documentPO.setSummary(hit.fields().get("summary").to(String.class));
        documentPO.setAuthLevel(hit.fields().get("auth_level").to(Integer.class));
        documentPO.setType(hit.fields().get("type").to(Integer.class));
        documentPO.setStatus(hit.fields().get("status").to(Integer.class));
        documentPO.setCreateTime(hit.fields().get("create_time").to(Long.class));
        documentPO.setUpdateTime(hit.fields().get("update_time").to(Long.class));
        return documentPO;
    }
}
