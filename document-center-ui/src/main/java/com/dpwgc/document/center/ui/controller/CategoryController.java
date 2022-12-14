package com.dpwgc.document.center.ui.controller;

import com.dpwgc.document.center.app.command.category.CategoryCommandService;
import com.dpwgc.document.center.app.query.category.CategoryQueryService;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.category.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 文档分类相关接口
 */
@Api(value = "文档分类相关接口")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    CategoryCommandService categoryCommandService;

    @Resource
    CategoryQueryService categoryQueryService;

    @ApiOperation(value = "创建分类")
    @PostMapping("/createCategory")
    @MutationMapping
    public ResultDTO<String> createCategory(@RequestBody @Validated CreateCategoryCommand createCategoryCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(categoryCommandService.createCategory(createCategoryCommand));
    }

    @ApiOperation(value = "更新分类信息")
    @PostMapping("/updateCategory")
    @MutationMapping
    public ResultDTO<Boolean> updateCategory(@RequestBody @Validated UpdateCategoryCommand updateCategoryCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(categoryCommandService.updateCategory(updateCategoryCommand));
    }

    @ApiOperation(value = "构建完整的分类树")
    @GetMapping("/buildCategoryTreeByAppId")
    @QueryMapping
    public ResultDTO<List<CategoryTreeDTO>> buildCategoryTreeByAppId(@ModelAttribute CategoryTreeQuery categoryTreeQuery) {
        return ResultDTO.getSuccessResult(categoryQueryService.buildCategoryTreeByAppId(categoryTreeQuery.getAppId(), categoryTreeQuery.getShowDetail()));
    }

    @ApiOperation(value = "根据父类id获取分类列表")
    @GetMapping("/queryCategoryByParentId")
    @QueryMapping
    public ResultDTO<List<CategoryDTO>> queryCategoryByParentId(@ModelAttribute CategoryListQuery categoryListQuery) {
        return ResultDTO.getSuccessResult(categoryQueryService.queryCategoryByParentId(categoryListQuery.getAppId(), categoryListQuery.getParentId(), categoryListQuery.getShowDetail()));
    }

    @ApiOperation(value = "根据分类id获取分类")
    @GetMapping("/queryCategoryByCategoryId")
    @QueryMapping
    public ResultDTO<CategoryDTO> queryCategoryByCategoryId(@ApiParam(value = "应用id") String appId,
                                                                  @ApiParam(value = "分类id") String categoryId) {
        return ResultDTO.getSuccessResult(categoryQueryService.queryCategoryByCategoryId(appId, categoryId));
    }
}
