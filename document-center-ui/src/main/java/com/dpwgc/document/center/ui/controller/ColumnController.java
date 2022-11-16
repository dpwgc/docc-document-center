package com.dpwgc.document.center.ui.controller;

import com.dpwgc.document.center.app.command.column.ColumnCommandService;
import com.dpwgc.document.center.app.query.column.ColumnQueryService;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.column.ColumnQuery;
import com.dpwgc.document.center.sdk.model.column.CreateColumnCommand;
import com.dpwgc.document.center.sdk.model.column.UpdateColumnCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public ResultDTO<String> createColumn(@RequestBody @Validated CreateColumnCommand createColumnCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(null);
    }

    @ApiOperation(value = "更新专栏信息")
    @PostMapping("/updateColumn")
    public ResultDTO<Boolean> updateColumn(@RequestBody @Validated UpdateColumnCommand updateColumnCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(null);
    }

    @ApiOperation(value = "查询专栏")
    @PostMapping("/queryColumn")
    public ResultDTO<String> queryColumn(@RequestBody @Validated ColumnQuery columnQuery, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(null);
    }
}
