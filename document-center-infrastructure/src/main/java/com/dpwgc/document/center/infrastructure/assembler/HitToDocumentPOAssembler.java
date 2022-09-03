package com.dpwgc.document.center.infrastructure.assembler;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dpwgc.document.center.infrastructure.dal.document.entity.DocumentPO;
import com.dpwgc.document.center.infrastructure.util.JsonUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HitToDocumentPOAssembler {

    /**
     * elasticsearch的Hit对象转DocumentPO对象
     */
    public DocumentPO assemblerDocumentPO(Hit<Object> hit) {
        String hitJson = JsonUtil.toJson(hit.source());
        return JsonUtil.fromJson(hitJson,DocumentPO.class);
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
