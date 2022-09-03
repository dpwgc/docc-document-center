package com.dpwgc.document.center.ui.interfaces.tag;

import com.dpwgc.document.center.app.command.tag.TagCommandService;
import com.dpwgc.document.center.app.query.tag.TagQueryService;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.tag.TagDTO;
import com.dpwgc.document.center.sdk.model.tag.UpdateTagCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public ResultDTO<Boolean> updateTag(UpdateTagCommand updateTagCommand) {
        return ResultDTO.getSuccessResult(tagCommandService.updateTag(updateTagCommand));
    }

    /**
     * 获取在指定时间区间内活跃的标签列表（按number文档数量降序排序）
     * @param appId 应用id
     * @param startUpdateTime 标签更新时间区间的起始位置
     * @param endUpdateTime 标签更新时间区间的结束位置
     * @param pageSize 返回标签数量上限
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "获取在指定时间区间内活跃的标签列表（按number文档数量降序排序）")
    @GetMapping("/listTagsByNumberDesc")
    public ResultDTO<List<TagDTO>> listTagsByNumberDesc(@ApiParam(value = "应用id") String appId,
                                                        @ApiParam(value = "标签更新时间区间的起始位置") Long startUpdateTime,
                                                        @ApiParam(value = "标签更新时间区间的结束位置") Long endUpdateTime,
                                                        @ApiParam(value = "返回标签数量上限") Integer pageSize) {

        return ResultDTO.getSuccessResult(tagQueryService.listTagsByNumberDesc(appId,startUpdateTime,endUpdateTime,pageSize));
    }
}
