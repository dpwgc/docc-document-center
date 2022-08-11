package com.dpwgc.document.center.infrastructure.assembler;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import com.dpwgc.document.center.sdk.model.document.DocumentAggregationsDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AggregationsMapToObjectAssembler {

    public DocumentAggregationsDTO assemblerAggregations(Map<String, Aggregate> map) {
        DocumentAggregationsDTO documentAggregationsDTO = new DocumentAggregationsDTO();
        documentAggregationsDTO.setDocumentTotal(Long.parseLong(map.get("documentTotal").toString()));
        documentAggregationsDTO.setCommentTotal(Long.parseLong(map.get("commentTotal").toString()));
        documentAggregationsDTO.setLikeTotal(Long.parseLong(map.get("likeTotal").toString()));
        documentAggregationsDTO.setLoveTotal(Long.parseLong(map.get("loveTotal").toString()));
        return documentAggregationsDTO;
    }
}
