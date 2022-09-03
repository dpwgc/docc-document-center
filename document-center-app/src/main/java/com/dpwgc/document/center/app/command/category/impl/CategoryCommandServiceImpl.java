package com.dpwgc.document.center.app.command.category.impl;

import com.dpwgc.document.center.app.assembler.CategoryAssembler;
import com.dpwgc.document.center.app.command.category.CategoryCommandService;
import com.dpwgc.document.center.domain.category.Category;
import com.dpwgc.document.center.domain.category.CategoryFactory;
import com.dpwgc.document.center.domain.category.CategoryRepository;
import com.dpwgc.document.center.infrastructure.util.IdGenUtil;
import com.dpwgc.document.center.sdk.model.category.CreateCategoryCommand;
import com.dpwgc.document.center.sdk.model.category.UpdateCategoryCommand;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class CategoryCommandServiceImpl implements CategoryCommandService {

    @Resource
    IdGenUtil idGenUtil;

    @Resource
    CategoryRepository categoryRepository;

    @Override
    public String createCategory(CreateCategoryCommand createCategoryCommand) {

        CategoryFactory categoryFactory = new CategoryFactory();
        Category category = categoryFactory.create(
                createCategoryCommand.getAppId(),
                idGenUtil.nextIdString(),
                createCategoryCommand.getParentId(),
                createCategoryCommand.getCategoryName(),
                createCategoryCommand.getDetail(),
                createCategoryCommand.getScore()
        );
        return categoryRepository.createCategory(category);
    }

    @Override
    public Boolean updateCategory(UpdateCategoryCommand updateCategoryCommand) {

        Category category = CategoryAssembler.INSTANCE.assembleCategoryFromUpdate(updateCategoryCommand);

        category.setUpdateTime(System.currentTimeMillis());

        return categoryRepository.updateCategory(category);
    }
}
