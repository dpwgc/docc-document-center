package com.dpwgc.document.center.infrastructure.assembler;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import com.dpwgc.document.center.sdk.model.document.DocumentAggregationsDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AggregationsMapToObjectAssembler {

    public DocumentAggregationsDTO assemblerAggregations(Map<String, Aggregate> map) {
        DocumentAggregationsDTO documentAggregationsDTO = new DocumentAggregationsDTO();
        documentAggregationsDTO.setDocumentTotal(map.get("documentTotal").sum().value());
        documentAggregationsDTO.setCommentTotal(map.get("commentTotal").sum().value());
        documentAggregationsDTO.setLikeTotal(map.get("likeTotal").sum().value());
        documentAggregationsDTO.setLoveTotal(map.get("loveTotal").sum().value());
        return documentAggregationsDTO;
    }
}
