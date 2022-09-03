package com.dpwgc.document.center.domain.category;

import com.dpwgc.document.center.sdk.model.category.UpdateCategoryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryAssembler {

    CategoryAssembler INSTANCE = Mappers.getMapper(CategoryAssembler.class);

    Category assembleCategoryFromUpdate(UpdateCategoryCommand updateCategoryCommand);
}
