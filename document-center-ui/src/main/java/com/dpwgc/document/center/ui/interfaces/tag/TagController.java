package com.dpwgc.document.center.ui.interfaces.tag;

import com.dpwgc.document.center.app.command.tag.TagCommandService;
import com.dpwgc.document.center.app.query.tag.TagQueryService;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.tag.DeleteTagCommand;
import com.dpwgc.document.center.sdk.model.tag.UpdateTagNumberCommand;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 文档标签-接口
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    TagCommandService tagCommandService;

    @Resource
    TagQueryService tagQueryService;

    /**
     * 后台修改标签数量
     */
    @PostMapping("updateTagNumber")
    public ResultDTO<Object> updateTagNumber(UpdateTagNumberCommand updateTagNumberCommand) {
        return ResultDTO.getSuccessResult(tagCommandService.updateTagNumber(updateTagNumberCommand));
    }

    /**
     * 删除（隐藏）标签
     */
    @PostMapping("deleteTag")
    public ResultDTO<Object> deleteTag(DeleteTagCommand deleteTagCommand) {
        return ResultDTO.getSuccessResult(tagCommandService.deleteTag(deleteTagCommand));
    }

    /**
     * 获取在指定时间区间内活跃的标签列表（按number文档数量降序排序）
     * @param appId 应用id
     * @param startUpdateTime 标签更新时间区间的起始位置
     * @param endUpdateTime 标签更新时间区间的结束位置
     * @param pageSize 最多返回几个标签
     * @return ResultDTO<Object>
     */
    @GetMapping("listTagsByNumberDesc")
    public ResultDTO<Object> listTagsByNumberDesc(String appId, Long startUpdateTime, Long endUpdateTime, Integer pageSize) {
        return ResultDTO.getSuccessResult(tagQueryService.listTagsByNumberDesc(appId,startUpdateTime,endUpdateTime,pageSize));
    }
}
