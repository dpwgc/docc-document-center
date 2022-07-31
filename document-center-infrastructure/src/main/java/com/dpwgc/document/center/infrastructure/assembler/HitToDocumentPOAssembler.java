package com.dpwgc.document.center.infrastructure.assembler;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HitToDocumentPOAssembler {

    /**
     * elasticsearch的Hit对象转DocumentPO对象
     */
    public DocumentPO assemblerDocumentPO(Hit<Object> hit) {
        DocumentPO documentPO = new DocumentPO();
        documentPO.setId(hit.id());
        documentPO.setAppId(((Map)hit.source()).get("app_id").toString());
        documentPO.setCategoryId(((Map)hit.source()).get("document_id").toString());
        documentPO.setAuthorId(((Map)hit.source()).get("author_id").toString());
        documentPO.setDocumentId(((Map)hit.source()).get("document_id").toString());
        documentPO.setTitle(((Map)hit.source()).get("title").toString());
        documentPO.setContent(((Map)hit.source()).get("content").toString());
        documentPO.setTags(((Map)hit.source()).get("tags").toString());
        documentPO.setSummary(((Map)hit.source()).get("summary").toString());
        documentPO.setAuthLevel(Integer.parseInt(((Map)hit.source()).get("auth_level").toString()));

        documentPO.setScore(Long.parseLong(((Map)hit.source()).get("score").toString()));
        documentPO.setLove(Long.parseLong(((Map)hit.source()).get("love").toString()));
        documentPO.setLike(Long.parseLong(((Map)hit.source()).get("like").toString()));
        documentPO.setRead(Long.parseLong(((Map)hit.source()).get("read").toString()));

        documentPO.setType(Integer.parseInt(((Map)hit.source()).get("type").toString()));
        documentPO.setStatus(Integer.parseInt(((Map)hit.source()).get("status").toString()));
        documentPO.setCreateTime(Long.parseLong(((Map)hit.source()).get("create_time").toString()));
        documentPO.setUpdateTime(Long.parseLong(((Map)hit.source()).get("update_time").toString()));
        return documentPO;
    }

    /**
     * elasticsearch的Hit列表转DocumentPO列表
     */
    public List<DocumentPO> assemblerDocumentPOList(List<Hit<Object>> hits) {
        List<DocumentPO> documentPOS = new ArrayList<>();
        for (Hit<Object> hit : hits) {
            documentPOS.add(assemblerDocumentPO(hit));
        }
        return documentPOS;
    }
}
