package com.dpwgc.document.center.ui.controller;

import com.dpwgc.document.center.app.command.tag.TagCommandService;
import com.dpwgc.document.center.app.query.tag.TagQueryService;
import com.dpwgc.document.center.infrastructure.util.LogUtil;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.tag.TagDTO;
import com.dpwgc.document.center.sdk.model.tag.TagQuery;
import com.dpwgc.document.center.sdk.model.tag.UpdateTagCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public ResultDTO<Boolean> updateTag(@RequestBody UpdateTagCommand updateTagCommand) {
        try {
            return ResultDTO.getSuccessResult(tagCommandService.updateTag(updateTagCommand));
        } catch (Exception e) {
            LogUtil.error("updateTag error",e.getMessage(),"tag");
            return ResultDTO.getFailureResult(e.getMessage());
        }
    }

    /**
     * 获取在指定时间区间内活跃的标签列表（按number文档数量降序排序）
     * @param tagQuery 检索条件
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "获取在指定时间区间内活跃的标签列表（按number文档数量降序排序）")
    @GetMapping("/listTagsByNumberDesc")
    public ResultDTO<List<TagDTO>> listTagsByNumberDesc(@ModelAttribute TagQuery tagQuery) {
        try {
            return ResultDTO.getSuccessResult(tagQueryService.listTagsByNumberDesc(tagQuery));
        } catch (Exception e) {
            LogUtil.error("listTagsByNumberDesc error",e.getMessage(),"tag");
            return ResultDTO.getFailureResult(e.getMessage());
        }
    }
}