package com.dpwgc.document.center.infrastructure.assembler;

import com.dpwgc.document.center.domain.category.Category;
import com.dpwgc.document.center.infrastructure.dal.category.entity.CategoryPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryPOAssembler {

    CategoryPOAssembler INSTANCE = Mappers.getMapper(CategoryPOAssembler.class);

    Category assembleCategory(CategoryPO documentPO);

    CategoryPO assembleCategoryPO(Category document);
}
