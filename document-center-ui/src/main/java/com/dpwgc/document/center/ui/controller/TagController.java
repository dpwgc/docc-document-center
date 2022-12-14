package com.dpwgc.document.center.ui.controller;

import com.dpwgc.document.center.app.command.tag.TagCommandService;
import com.dpwgc.document.center.app.query.tag.TagQueryService;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.tag.TagDTO;
import com.dpwgc.document.center.sdk.model.tag.TagQuery;
import com.dpwgc.document.center.sdk.model.tag.UpdateTagCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 文档标签相关接口
 */
@Api(value = "文档标签相关接口")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    TagCommandService tagCommandService;

    @Resource
    TagQueryService tagQueryService;

    /**
     * 修改标签数量/隐藏标签
     */
    @ApiOperation(value = "后台修改标签")
    @PostMapping("/updateTag")
    @MutationMapping
    public ResultDTO<Boolean> updateTag(@RequestBody @Validated UpdateTagCommand updateTagCommand, BindingResult bindingResult) {
        return ResultDTO.getSuccessResult(tagCommandService.updateTag(updateTagCommand));
    }

    /**
     * 获取在指定时间区间内活跃的标签列表
     * @param tagQuery 检索条件
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "获取在指定时间区间内活跃的标签列表")
    @GetMapping("/queryTag")
    @QueryMapping
    public ResultDTO<PageBase<List<TagDTO>>> queryTag(@ModelAttribute TagQuery tagQuery) {
        return ResultDTO.getSuccessResult(tagQueryService.queryTag(tagQuery));
    }
}
