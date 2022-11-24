package com.dpwgc.document.center.domain.column;

public class ColumnFactory {
    public Column create(String appId,String columnId,String authorId,String columnName,String detail,String extra,Long score,Integer attr,Integer type) {
        return new Column().create(appId, columnId, authorId, columnName, detail, extra, score, attr, type);
    }
}
