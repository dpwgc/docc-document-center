package com.dpwgc.document.center.ui.interfaces.category;

import com.dpwgc.document.center.app.command.category.CategoryCommandService;
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

    @PostMapping("createCategory")
    public ResultDTO<Object> createCategory(@RequestBody CreateCategoryCommand createCategoryCommand) {
        return ResultDTO.getSuccessResult(null);
    }

    @PostMapping("updateCategory")
    public ResultDTO<Object> updateCategory(@RequestBody UpdateCategoryCommand updateCategoryCommand) {
        return ResultDTO.getSuccessResult(null);
    }

    @PostMapping("deleteCategory")
    public ResultDTO<Object> deleteCategory(@RequestBody DeleteCategoryCommand deleteCategoryCommand) {
        return ResultDTO.getSuccessResult(null);
    }

    @GetMapping("getCategoryTreeByAppId")
    public ResultDTO<Object> getCategoryTreeByAppId(String appId) {
        return ResultDTO.getSuccessResult(null);
    }
}
