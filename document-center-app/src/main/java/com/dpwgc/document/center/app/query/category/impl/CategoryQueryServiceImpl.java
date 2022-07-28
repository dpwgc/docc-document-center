package com.dpwgc.document.center.app.query.category.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.app.assembler.CategoryAssembler;
import com.dpwgc.document.center.app.query.category.CategoryQueryService;
import com.dpwgc.document.center.infrastructure.dal.category.entity.CategoryPO;
import com.dpwgc.document.center.infrastructure.dal.category.mapper.CategoryMapper;
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
        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("status",1);
        List<CategoryPO> categoryPOS = categoryMapper.selectList(queryWrapper);
        List<CategoryTreeDTO> categoryTreeDTOS = new ArrayList<>();
        for (CategoryPO categoryPO : categoryPOS) {
            categoryTreeDTOS.add(CategoryAssembler.INSTANCE.assemblerCategoryTreeDTO(categoryPO));
        }
        return categoryListToTree(categoryTreeDTOS);
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
            //尝试从哈希表中找到该类的父类别parent
            CategoryTreeDTO parent = hashMap.get(c.getParentId());

            //如果找着父类别
            if(parent.getParentId() != null && parent.getParentId().length() > 0){
                //则将该类别添加进父类别parent的子类别列表中
                parent.getChildren().add(c);
            }else{
                //如果没找着父类别，说明他是最顶层类别，直接将其加入树顶
                result.add(c);
            }
        });
        return result;
    }
}
