package com.dpwgc.document.center.sdk.base;

public class PageBase<T> {
    private Long total;
    private T list;

    public static <T> PageBase<T> getPageBase(Long total, T list) {
        PageBase<T> pageBase = new PageBase();
        pageBase.total = total;
        pageBase.list = list;
        return pageBase;
    }

    public void setList(T list) {
        this.list = list;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public T getList() {
        return list;
    }
}
