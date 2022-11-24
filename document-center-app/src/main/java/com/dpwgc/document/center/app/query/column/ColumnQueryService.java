package com.dpwgc.document.center.app.query.column;

import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.model.column.ColumnDTO;
import com.dpwgc.document.center.sdk.model.column.ColumnQuery;

import java.util.List;

public interface ColumnQueryService {

    ColumnDTO queryColumnByColumnId(String appId,String columnId);

    PageBase<List<ColumnDTO>> queryColumn(ColumnQuery columnQuery);
}
