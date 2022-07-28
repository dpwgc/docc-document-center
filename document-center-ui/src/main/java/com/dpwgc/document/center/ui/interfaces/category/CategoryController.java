package com.dpwgc.document.center.ui.interfaces.category;

import com.dpwgc.document.center.app.command.category.CategoryCommandService;
import com.dpwgc.document.center.app.query.category.CategoryQueryService;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.category.CreateCategoryCommand;
import com.dpwgc.document.center.sdk.model.category.DeleteCategoryCommand;
import com.dpwgc.document.center.sdk.model.category.UpdateCategoryCommand;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    CategoryCommandService categoryCommandService;

    @Resource
    CategoryQueryService categoryQueryService;

    @PostMapping("createCategory")
    public ResultDTO<Object> createCategory(@RequestBody CreateCategoryCommand createCategoryCommand) {
        return ResultDTO.getSuccessResult(categoryCommandService.createCategory(createCategoryCommand));
    }

    @PostMapping("updateCategory")
    public ResultDTO<Object> updateCategory(@RequestBody UpdateCategoryCommand updateCategoryCommand) {
        return ResultDTO.getSuccessResult(categoryCommandService.updateCategory(updateCategoryCommand));
    }

    @PostMapping("deleteCategory")
    public ResultDTO<Object> deleteCategory(@RequestBody DeleteCategoryCommand deleteCategoryCommand) {
        return ResultDTO.getSuccessResult(categoryCommandService.deleteCategory(deleteCategoryCommand));
    }

    @GetMapping("getCategoryTreeByAppId")
    public ResultDTO<Object> getCategoryTreeByAppId(String appId) {
        return ResultDTO.getSuccessResult(categoryQueryService.getCategoryTreeByAppId(appId));
    }
}
