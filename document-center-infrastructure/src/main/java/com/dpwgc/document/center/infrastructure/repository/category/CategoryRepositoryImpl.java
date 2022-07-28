package com.dpwgc.document.center.infrastructure.repository.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.domain.category.Category;
import com.dpwgc.document.center.domain.category.CategoryRepository;
import com.dpwgc.document.center.infrastructure.assembler.CategoryPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.category.entity.CategoryPO;
import com.dpwgc.document.center.infrastructure.dal.category.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public Category queryCategoryByCategoryId(String categoryId) {

        QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",categoryId);
        queryWrapper.eq("status",1);

        return CategoryPOAssembler.INSTANCE.assembleCategory(categoryMapper.selectOne(queryWrapper));
    }

    @Override
    public String createCategory(Category category) {
        if (categoryMapper.insert(CategoryPOAssembler.INSTANCE.assembleCategoryPO(category))>0) {
            return category.getCategoryId();
        }
        return null;
    }

    @Override
    public Boolean updateCategory(Category category) {

        QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",category.getCategoryId());
        queryWrapper.eq("status",1);

        CategoryPO categoryPO = CategoryPOAssembler.INSTANCE.assembleCategoryPO(category);

        //更新时间
        categoryPO.setUpdateTime(System.currentTimeMillis());

        return categoryMapper.update(categoryPO, queryWrapper) > 0;
    }

    @Override
    public Boolean deleteCategory(String categoryId) {

        QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",categoryId);
        queryWrapper.eq("status",1);

        CategoryPO categoryPO = categoryMapper.selectOne(queryWrapper);

        categoryPO.setStatus(0);                                //将状态设为0
        categoryPO.setUpdateTime(System.currentTimeMillis());   //更新时间

        return categoryMapper.update(categoryPO, queryWrapper) > 0;
    }
}
