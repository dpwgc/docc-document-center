package com.dpwgc.document.center.infrastructure.repository.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.domain.category.Category;
import com.dpwgc.document.center.domain.category.CategoryRepository;
import com.dpwgc.document.center.infrastructure.assembler.CategoryPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.category.entity.CategoryPO;
import com.dpwgc.document.center.infrastructure.dal.category.mapper.CategoryMapper;
import com.dpwgc.document.center.sdk.base.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Value("${optimistic.update.retry}")
    private int retry;

    @Resource
    CategoryMapper categoryMapper;

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

        // 判断是否存在 + 版本号获取
        queryWrapper.select("version");
        CategoryPO po = categoryMapper.selectOne(queryWrapper);
        if (po == null) {
            return false;
        }

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
        //带上版本号（乐观锁更新）
        categoryPO.setVersion(po.getVersion());
        //更新时间
        categoryPO.setUpdateTime(System.currentTimeMillis());

        for (int i=0;i<retry;i++) {
            //如果成功
            if (categoryMapper.update(categoryPO, queryWrapper) > 0) {
                return true;
            }
            //如果失败，重新获取版本，再次更新
            po = categoryMapper.selectOne(queryWrapper);
            categoryPO.setVersion(po.getVersion());
        }
        return false;
    }
}
