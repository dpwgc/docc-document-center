package com.dpwgc.document.center.app.query.category;

import com.dpwgc.document.center.sdk.model.category.CategoryTreeDTO;

import java.util.List;

public interface CategoryQueryService {

    List<CategoryTreeDTO> getCategoryTreeByAppId(String appId);

    String queryDetailByCategoryId(String categoryId);
}
