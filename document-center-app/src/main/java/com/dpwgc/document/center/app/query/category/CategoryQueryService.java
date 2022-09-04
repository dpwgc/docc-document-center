package com.dpwgc.document.center.app.query.category;

import com.dpwgc.document.center.sdk.model.category.CategoryDTO;
import com.dpwgc.document.center.sdk.model.category.CategoryDetailDTO;
import com.dpwgc.document.center.sdk.model.category.CategoryTreeDTO;

import java.util.List;

public interface CategoryQueryService {

    List<CategoryTreeDTO> getCategoryTreeByAppId(String appId);

    List<CategoryDTO> queryCategoryByParentId(String appId, String parentId);

    CategoryDetailDTO queryDetailByCategoryId(String appId, String categoryId);
}
