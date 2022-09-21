package com.dpwgc.document.center.infrastructure.assembler;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import com.dpwgc.document.center.sdk.model.document.AggregationsDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AggregationsMapToObjectAssembler {

    public AggregationsDTO assemblerAggregations(Map<String, Aggregate> map) {
        AggregationsDTO aggregationsDTO = new AggregationsDTO();
        aggregationsDTO.setDocumentTotal(map.get("documentTotal").sum().value());
        aggregationsDTO.setCommentTotal(map.get("commentTotal").sum().value());
        aggregationsDTO.setLikeTotal(map.get("likeTotal").sum().value());
        aggregationsDTO.setLoveTotal(map.get("loveTotal").sum().value());
        aggregationsDTO.setLoveTotal(map.get("shareTotal").sum().value());
        return aggregationsDTO;
    }
}
