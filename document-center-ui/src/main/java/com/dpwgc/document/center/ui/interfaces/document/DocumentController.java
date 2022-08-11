package com.dpwgc.document.center.ui.interfaces.document;

import com.dpwgc.document.center.app.command.document.DocumentCommandService;
import com.dpwgc.document.center.app.query.document.DocumentQueryService;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.document.SearchDocumentQuery;
import com.dpwgc.document.center.sdk.model.document.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 文档相关接口
 */
@Api(value = "文档相关接口")
@RestController
@RequestMapping("/document")
public class DocumentController {

    @Resource
    DocumentCommandService documentCommandService;

    @Resource
    DocumentQueryService documentQueryService;

    /**
     * 新建文档
     */
    @ApiOperation(value = "新建文档")
    @PostMapping("/createDocument")
    public ResultDTO<String> createDocument(@RequestBody CreateDocumentCommand createDocumentCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.createDocument(createDocumentCommand));
    }

    /**
     * 更新文档主体内容（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档主体内容（匹配ES主键id）")
    @PostMapping("/updateDocumentMain")
    public ResultDTO<Boolean> updateDocumentMain(@RequestBody UpdateDocumentMainCommand updateDocumentMainCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentMain(updateDocumentMainCommand));
    }

    /**
     * 更新文档权限等级（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档权限等级（匹配ES主键id）")
    @PostMapping("/updateDocumentAuthLevel")
    public ResultDTO<Boolean> updateDocumentAuthLevel(@RequestBody UpdateDocumentAuthLevelCommand updateDocumentAuthLevelCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentAuthLevel(updateDocumentAuthLevelCommand));
    }

    /**
     * 更新文档类型（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档类型（匹配ES主键id）")
    @PostMapping("/updateDocumentType")
    public ResultDTO<Boolean> updateDocumentType(@RequestBody UpdateDocumentTypeCommand updateDocumentTypeCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentType(updateDocumentTypeCommand));
    }

    /**
     * 更新文档推荐评分（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档推荐评分（匹配ES主键id）")
    @PostMapping("/updateDocumentScore")
    public ResultDTO<Boolean> updateDocumentScore(@RequestBody UpdateDocumentScoreCommand updateDocumentScoreCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentScore(updateDocumentScoreCommand));
    }

    /**
     * 更新文档收藏数（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档收藏数（匹配ES主键id）")
    @PostMapping("/updateDocumentLove")
    public ResultDTO<Boolean> updateDocumentLove(@RequestBody UpdateDocumentLoveCommand updateDocumentLoveCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentLove(updateDocumentLoveCommand));
    }

    /**
     * 更新文档点赞数（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档点赞数（匹配ES主键id）")
    @PostMapping("/updateDocumentLike")
    public ResultDTO<Boolean> updateDocumentLike(@RequestBody UpdateDocumentLikeCommand updateDocumentLikeCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentLike(updateDocumentLikeCommand));
    }

    /**
     * 更新文档阅读数（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档阅读数（匹配ES主键id）")
    @PostMapping("/updateDocumentRead")
    public ResultDTO<Boolean> updateDocumentRead(@RequestBody UpdateDocumentReadCommand updateDocumentReadCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentRead(updateDocumentReadCommand));
    }

    /**
     * 更新文档评论数（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档评论数（匹配ES主键id）")
    @PostMapping("/updateDocumentCommentNum")
    public ResultDTO<Boolean> updateDocumentCommentNum(@RequestBody UpdateDocumentCommentNumCommand updateDocumentCommentNumCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentCommentNum(updateDocumentCommentNumCommand));
    }

    /**
     * 删除文档（匹配ES主键id）
     */
    @ApiOperation(value = "删除文档（匹配ES主键id）")
    @PostMapping("/deleteDocument")
    public ResultDTO<Boolean> deleteDocument(@RequestBody DeleteDocumentCommand deleteDocumentCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.deleteDocument(deleteDocumentCommand));
    }

    /**
     * 根据ES主键id查询文档
     * （获取文档详情）
     * @param id ES主键id
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据ES主键id查询文档")
    @GetMapping("/queryDocumentById")
    ResultDTO<DocumentDTO> queryDocumentById(@ApiParam(value = "ES主键id") String id) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentById(id));
    }

    /**
     * 根据ES主键id集合查询文档
     * （可以用这接口来查询用户的收藏文档列表）
     * @param idList ES主键id集合
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据ES主键id集合查询文档")
    @GetMapping("/queryDocumentByIdList")
    ResultDTO<List<DocumentDTO>> queryDocumentByIdList(@ApiParam(value = "ES主键id集合") List<String> idList) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByIdList(idList));
    }

    /**
     * 文档检索
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "文档检索")
    @GetMapping("/searchDocument")
    public ResultDTO<PageBase<List<DocumentDTO>>> searchDocument(@ModelAttribute SearchDocumentQuery searchDocumentQuery) {
        return ResultDTO.getSuccessResult(documentQueryService.searchDocument(searchDocumentQuery));
    }

    /**
     * 文档数据聚合统计
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "文档数据聚合统计")
    @GetMapping("/aggregationsDocument")
    public ResultDTO<DocumentAggregationsDTO> aggregationsDocument(@ModelAttribute AggregationsDocumentQuery aggregationsDocumentQuery) {
        return ResultDTO.getSuccessResult(documentQueryService.aggregationsDocument(aggregationsDocumentQuery));
    }
}
