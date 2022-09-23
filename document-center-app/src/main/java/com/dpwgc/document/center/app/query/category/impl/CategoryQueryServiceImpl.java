package com.dpwgc.document.center.app.query.category.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.app.assembler.CategoryAssembler;
import com.dpwgc.document.center.app.query.category.CategoryQueryService;
import com.dpwgc.document.center.infrastructure.dal.category.entity.CategoryPO;
import com.dpwgc.document.center.infrastructure.dal.category.mapper.CategoryMapper;
import com.dpwgc.document.center.infrastructure.util.StringUtil;
import com.dpwgc.document.center.sdk.base.Status;
import com.dpwgc.document.center.sdk.model.category.CategoryDTO;
import com.dpwgc.document.center.sdk.model.category.CategoryDetailDTO;
import com.dpwgc.document.center.sdk.model.category.CategoryTreeDTO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {

    @Resource
    CategoryMapper categoryMapper;

    public List<CategoryTreeDTO> getCategoryTreeByAppId(String appId) {

        QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();

        //要查询的字段
        queryWrapper.select("category_id","parent_id","category_name","score","create_time","update_time");

        if (StringUtil.notEmpty(appId)) {
            queryWrapper.eq("app_id",appId);
        }
        queryWrapper.eq("status",Status.NORMAL);
        queryWrapper.orderByDesc("score");  //类别树的各级将按照score字段降序排序

        List<CategoryPO> categoryPOS = categoryMapper.selectList(queryWrapper);
        List<CategoryTreeDTO> categoryTreeDTOS = new ArrayList<>();
        for (CategoryPO categoryPO : categoryPOS) {
            categoryTreeDTOS.add(CategoryAssembler.INSTANCE.assemblerCategoryTreeDTO(categoryPO));
        }
        return categoryListToTree(categoryTreeDTOS);
    }

    public List<CategoryDTO> queryCategoryByParentId(String appId, String parentId) {

        QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();

        //要查询的字段
        queryWrapper.select("category_id","parent_id","category_name","score","create_time","update_time");

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("parent_id",parentId);
        queryWrapper.eq("status",Status.NORMAL);
        queryWrapper.orderByDesc("score");  //类别树的各级将按照score字段降序排序

        List<CategoryPO> categoryPOS = categoryMapper.selectList(queryWrapper);
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (CategoryPO categoryPO : categoryPOS) {
            categoryDTOS.add(CategoryAssembler.INSTANCE.assembleCategoryDTO(categoryPO));
        }
        return categoryDTOS;
    }

    /**
     * 类别列表转换成树结构
     * @param categoryTreeDTOS 类别列表
     * @return List<CategoryTreeDTO>
     */
    private List<CategoryTreeDTO> categoryListToTree(List<CategoryTreeDTO> categoryTreeDTOS){
        Map<String,CategoryTreeDTO> hashMap = new HashMap<>(32);

        //类别级联树
        List<CategoryTreeDTO> result = new ArrayList<>(); //保证add时线程安全

        //遍历所有类别
        categoryTreeDTOS.forEach(c->{
            //初始化所有类别的子类别列表
            c.setChildren(new ArrayList<>()); //保证add时线程安全
            //将所有类别放入哈希表，key为类别id，value为类别对象
            hashMap.put(c.getCategoryId(),c);
        });

        //遍历所有类别
        categoryTreeDTOS.forEach(c->{

            //如果没有父类别id，则说明它是顶层类别，直接加入map
            if (c.getParentId() == null || c.getParentId().equals("")) {
                result.add(c);
            } else {
                //尝试从哈希表中找到该类的父类别parent
                CategoryTreeDTO parent = hashMap.get(c.getParentId());

                //如果找着父类别
                if(parent.getParentId() != null && parent.getParentId().length() > 0){
                    //则将该类别添加进父类别parent的子类别列表中
                    parent.getChildren().add(c);
                }
                //如果没找着父类别，说明他的父类别已被删除，忽略它
            }
        });
        return result;
    }

    /**
     * 根据分类id获取分类详情
     * @param categoryId 分类id
     * @return CategoryDTO
     */
    @Override
    public CategoryDetailDTO queryDetailByCategoryId(String appId, String categoryId) {

        QueryWrapper<CategoryPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("category_id",categoryId);
        queryWrapper.eq("status", Status.NORMAL);

        CategoryPO categoryPO = categoryMapper.selectOne(queryWrapper);
        return CategoryAssembler.INSTANCE.assembleCategoryDetailDTO(categoryPO);
    }
}
