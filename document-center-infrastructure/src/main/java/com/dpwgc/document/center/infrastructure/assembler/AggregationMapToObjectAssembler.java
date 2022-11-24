package com.dpwgc.document.center.infrastructure.assembler;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import com.dpwgc.document.center.sdk.model.document.AggregationDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AggregationMapToObjectAssembler {

    public AggregationDTO assemblerAggregation(Map<String, Aggregate> map) {
        AggregationDTO aggregationDTO = new AggregationDTO();
        aggregationDTO.setDocumentTotal(map.get("documentTotal").sum().value());
        aggregationDTO.setCommentTotal(map.get("commentTotal").sum().value());
        aggregationDTO.setLikeTotal(map.get("likeTotal").sum().value());
        aggregationDTO.setLoveTotal(map.get("loveTotal").sum().value());
        aggregationDTO.setShareTotal(map.get("shareTotal").sum().value());
        aggregationDTO.setReadTotal(map.get("readTotal").sum().value());
        return aggregationDTO;
    }
}
