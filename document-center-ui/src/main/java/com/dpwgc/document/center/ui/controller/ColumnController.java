package com.dpwgc.document.center.ui.controller;

import com.dpwgc.document.center.app.command.column.ColumnCommandService;
import com.dpwgc.document.center.app.query.column.ColumnQueryService;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.column.*;
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
 * 文档专栏相关接口
 */
@Api(value = "文档专栏相关接口")
@RestController
@RequestMapping("/column")
public class ColumnController {

    @Resource
    ColumnCommandService columnCommandService;

    @Resource
    ColumnQueryService columnQueryService;

    @ApiOperation(value = "创建专栏")
    @PostMapping("/createColumn")
    @MutationMapping
    public ResultDTO<String> createColumn(@RequestBody @Validated CreateColumnCommand createColumnCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(columnCommandService.createColumn(createColumnCommand));
    }

    @ApiOperation(value = "更新专栏信息")
    @PostMapping("/updateColumn")
    @MutationMapping
    public ResultDTO<Boolean> updateColumn(@RequestBody @Validated UpdateColumnCommand updateColumnCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(columnCommandService.updateColumn(updateColumnCommand));
    }

    @ApiOperation(value = "查询专栏")
    @GetMapping("/queryColumn")
    @QueryMapping
    public ResultDTO<PageBase<List<ColumnDTO>>> queryColumn(@ModelAttribute ColumnQuery columnQuery) {
        return ResultDTO.getSuccessResult(columnQueryService.queryColumn(columnQuery));
    }

    @ApiOperation(value = "根据专栏id查询专栏详情")
    @GetMapping("/queryColumnByColumnId")
    @QueryMapping
    public ResultDTO<ColumnDTO> queryDetailByColumnId(@ApiParam(value = "应用id") String appId,
                                                      @ApiParam(value = "专栏id") String columnId) {
        return ResultDTO.getSuccessResult(columnQueryService.queryColumnByColumnId(appId, columnId));
    }
}
