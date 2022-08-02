package com.dpwgc.document.center.infrastructure.repository.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.domain.category.Category;
import com.dpwgc.document.center.domain.category.CategoryRepository;
import com.dpwgc.document.center.infrastructure.assembler.CategoryPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.category.entity.CategoryPO;
import com.dpwgc.document.center.infrastructure.dal.category.mapper.CategoryMapper;
import com.dpwgc.document.center.sdk.base.Status;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public Category queryCategoryByCategoryId(String appId, String categoryId) {

        QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("category_id",categoryId);
        queryWrapper.eq("status",Status.NORMAL);

        return CategoryPOAssembler.INSTANCE.assembleCategory(categoryMapper.selectOne(queryWrapper));
    }

    @Override
    public String createCategory(Category category) {

        //检查分类名称是否已经存在
        QueryWrapper<CategoryPO> checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("category_name",category.getCategoryName());
        checkWrapper.eq("app_id",category.getAppId());
        checkWrapper.eq("status",Status.NORMAL);
        if (categoryMapper.selectOne(checkWrapper) != null) {
            return null;
        }

        if (categoryMapper.insert(CategoryPOAssembler.INSTANCE.assembleCategoryPO(category))>0) {
            return category.getCategoryId();
        }
        return null;
    }

    @Override
    public Boolean updateCategory(Category category) {

        QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",category.getCategoryId());
        queryWrapper.eq("status", Status.NORMAL);

        //检查分类名称是否已经存在
        QueryWrapper<CategoryPO> checkWrapper = new QueryWrapper<>();
        checkWrapper.eq("category_name",category.getCategoryName());
        checkWrapper.eq("app_id",categoryMapper.selectOne(queryWrapper).getAppId());
        checkWrapper.eq("status",Status.NORMAL);
        CategoryPO checkPO = categoryMapper.selectOne(checkWrapper);
        //如果检查到分类名称已存在
        if (checkPO != null) {
            //如果不是自己，则说明新分类名称与其他分类名称重复
            if (!checkPO.getCategoryId().equals(category.getCategoryId())) {
                return false;
            }
        }

        CategoryPO categoryPO = CategoryPOAssembler.INSTANCE.assembleCategoryPO(category);

        //更新时间
        categoryPO.setUpdateTime(System.currentTimeMillis());

        return categoryMapper.update(categoryPO, queryWrapper) > 0;
    }

    @Override
    public Boolean deleteCategory(String appId, String categoryId) {

        QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("category_id",categoryId);
        queryWrapper.eq("status",Status.NORMAL);

        CategoryPO categoryPO = categoryMapper.selectOne(queryWrapper);

        categoryPO.setStatus(0);                                //将状态设为0
        categoryPO.setUpdateTime(System.currentTimeMillis());   //更新时间

        return categoryMapper.update(categoryPO, queryWrapper) > 0;
    }
}
