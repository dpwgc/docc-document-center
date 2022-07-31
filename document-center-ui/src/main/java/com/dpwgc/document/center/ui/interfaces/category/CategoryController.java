package com.dpwgc.document.center.ui.interfaces.category;

import com.dpwgc.document.center.app.command.category.CategoryCommandService;
import com.dpwgc.document.center.app.query.category.CategoryQueryService;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.category.CreateCategoryCommand;
import com.dpwgc.document.center.sdk.model.category.DeleteCategoryCommand;
import com.dpwgc.document.center.sdk.model.category.UpdateCategoryCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

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
    @PostMapping("createCategory")
    public ResultDTO<Object> createCategory(@RequestBody CreateCategoryCommand createCategoryCommand) {
        return ResultDTO.getSuccessResult(categoryCommandService.createCategory(createCategoryCommand));
    }

    @ApiOperation(value = "更新分类信息")
    @PostMapping("updateCategory")
    public ResultDTO<Object> updateCategory(@RequestBody UpdateCategoryCommand updateCategoryCommand) {
        return ResultDTO.getSuccessResult(categoryCommandService.updateCategory(updateCategoryCommand));
    }

    @ApiOperation(value = "删除（隐藏）分类")
    @PostMapping("deleteCategory")
    public ResultDTO<Object> deleteCategory(@RequestBody DeleteCategoryCommand deleteCategoryCommand) {
        return ResultDTO.getSuccessResult(categoryCommandService.deleteCategory(deleteCategoryCommand));
    }

    @ApiOperation(value = "获取分类树")
    @GetMapping("getCategoryTreeByAppId")
    public ResultDTO<Object> getCategoryTreeByAppId(String appId) {
        return ResultDTO.getSuccessResult(categoryQueryService.getCategoryTreeByAppId(appId));
    }

    @ApiOperation(value = "根据分类id获取分类详情")
    @GetMapping("queryDetailByCategoryId")
    public ResultDTO<Object> queryDetailByCategoryId(String categoryId) {
        return ResultDTO.getSuccessResult(categoryQueryService.queryDetailByCategoryId(categoryId));
    }
}
