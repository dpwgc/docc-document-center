package com.dpwgc.document.center.ui.controller;

import com.dpwgc.document.center.app.command.category.CategoryCommandService;
import com.dpwgc.document.center.app.query.category.CategoryQueryService;
import com.dpwgc.document.center.infrastructure.util.LogUtil;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.category.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public ResultDTO<String> createCategory(@RequestBody CreateCategoryCommand createCategoryCommand) {
        try {
            return ResultDTO.getSuccessResult(categoryCommandService.createCategory(createCategoryCommand));
        } catch (Exception e) {
            LogUtil.error("createCategory error",e.getMessage(),"category");
            return ResultDTO.getFailureResult(e.getMessage());
        }
    }

    @ApiOperation(value = "更新分类信息")
    @PostMapping("/updateCategory")
    public ResultDTO<Boolean> updateCategory(@RequestBody UpdateCategoryCommand updateCategoryCommand) {
        try {
            return ResultDTO.getSuccessResult(categoryCommandService.updateCategory(updateCategoryCommand));
        } catch (Exception e) {
            LogUtil.error("updateCategory error",e.getMessage(),"category");
            return ResultDTO.getFailureResult(e.getMessage());
        }
    }

    @ApiOperation(value = "获取分类树")
    @GetMapping("/getCategoryTreeByAppId")
    public ResultDTO<List<CategoryTreeDTO>> getCategoryTreeByAppId(@ApiParam(value = "应用id") String appId) {
        try {
            return ResultDTO.getSuccessResult(categoryQueryService.getCategoryTreeByAppId(appId));
        } catch (Exception e) {
            LogUtil.error("getCategoryTreeByAppId error",e.getMessage(),"category");
            return ResultDTO.getFailureResult(e.getMessage());
        }
    }

    @ApiOperation(value = "根据父类id获取分类列表")
    @GetMapping("/queryCategoryByParentId")
    public ResultDTO<List<CategoryDTO>> queryCategoryByParentId(@ApiParam(value = "应用id") String appId,
                                                                @ApiParam(value = "父类id") String parentId) {
        try {
            return ResultDTO.getSuccessResult(categoryQueryService.queryCategoryByParentId(appId, parentId));
        } catch (Exception e) {
            LogUtil.error("queryCategoryByParentId error",e.getMessage(),"category");
            return ResultDTO.getFailureResult(e.getMessage());
        }
    }

    @ApiOperation(value = "根据分类id获取分类详情")
    @GetMapping("/queryDetailByCategoryId")
    public ResultDTO<CategoryDetailDTO> queryDetailByCategoryId(@ApiParam(value = "应用id") String appId,
                                                                @ApiParam(value = "分类id") String categoryId) {
        try {
            return ResultDTO.getSuccessResult(categoryQueryService.queryDetailByCategoryId(appId, categoryId));
        } catch (Exception e) {
            LogUtil.error("queryDetailByCategoryId error",e.getMessage(),"category");
            return ResultDTO.getFailureResult(e.getMessage());
        }
    }
}
