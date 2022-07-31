package com.dpwgc.document.center.sdk.common;

import co.elastic.clients.elasticsearch._types.SortOrder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentQueryCommon {
    private Integer authLevel;
    private String sortField;
    private SortOrder sortOrder;
    private Integer pageIndex;
    private Integer pageSize;

    public void create(Integer authLevel, String sortField, String sortOrder, Integer pageIndex, Integer pageSize) {

        this.authLevel = authLevel;

        this.sortField = sortField;

        if (sortOrder.equals("Asc")) {
            this.sortOrder = SortOrder.Asc;
        } else {
            this.sortOrder = SortOrder.Desc;
        }

        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
