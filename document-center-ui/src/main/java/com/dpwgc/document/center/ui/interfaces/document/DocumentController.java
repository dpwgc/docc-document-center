package com.dpwgc.document.center.ui.interfaces.document;

import com.dpwgc.document.center.app.command.document.DocumentCommandService;
import com.dpwgc.document.center.app.query.document.DocumentQueryService;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.common.DocumentQueryCommon;
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
     * 删除文档（匹配ES主键id）
     */
    @ApiOperation(value = "删除文档（匹配ES主键id）")
    @PostMapping("/deleteDocument")
    public ResultDTO<Boolean> deleteDocument(@RequestBody DeleteDocumentCommand deleteDocumentCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.deleteDocument(deleteDocumentCommand));
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
     * 查询应用内的所有文档列表
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "查询应用内的所有文档列表")
    @GetMapping("/queryDocument")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocument(@ModelAttribute DocumentQueryCommon documentQueryCommon) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocument(documentQueryCommon));
    }

    /**
     * 根据关键词检索应用内的所有文档
     * @param keyword 关键词
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据关键词检索应用内的所有文档")
    @GetMapping("/queryDocumentByKeyword")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByKeyword(@ApiParam(value = "关键词") String keyword,
                                                                         @ModelAttribute DocumentQueryCommon documentQueryCommon) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByKeyword(keyword, documentQueryCommon));
    }

    /**
     * 根据分类id检索文档
     * @param categoryId 分类id
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据分类id检索文档")
    @GetMapping("/queryDocumentByCategoryId")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByCategoryId(@ApiParam(value = "分类id") String categoryId,
                                                                            @ModelAttribute DocumentQueryCommon documentQueryCommon) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByCategoryId(categoryId, documentQueryCommon));
    }

    /**
     * 根据分类id与关键词检索文档
     * @param categoryId 分类id
     * @param keyword 关键词
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据分类id与关键词检索文档")
    @GetMapping("/queryDocumentByCategoryIdAndKeyword")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByCategoryIdAndKeyword(@ApiParam(value = "分类id") String categoryId,
                                                                                      @ApiParam(value = "关键词") String keyword,
                                                                                      @ModelAttribute DocumentQueryCommon documentQueryCommon) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByCategoryIdAndKeyword(categoryId, keyword, documentQueryCommon));
    }

    /**
     * 根据分类id与文档类型type检索文档
     * @param categoryId 分类id
     * @param type 文档类型
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据分类id与文档类型type检索文档")
    @GetMapping("/queryDocumentByCategoryIdAndType")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByCategoryIdAndType(@ApiParam(value = "分类id") String categoryId,
                                                                                   @ApiParam(value = "文档类型") Integer type,
                                                                                   @ModelAttribute DocumentQueryCommon documentQueryCommon) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByCategoryIdAndType(categoryId, type, documentQueryCommon));
    }

    /**
     * 根据作者id检索文档
     * @param authorId 作者id
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据作者id检索文档")
    @GetMapping("/queryDocumentByAuthorId")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByAuthorId(@ApiParam(value = "作者id") String authorId,
                                                                          @ModelAttribute DocumentQueryCommon documentQueryCommon) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByAuthorId(authorId, documentQueryCommon));
    }

    /**
     * 根据作者id与关键词检索文档
     * @param authorId 作者id
     * @param keyword 关键词
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据作者id与关键词检索文档")
    @GetMapping("/queryDocumentByAuthorIdAndKeyword")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByAuthorIdAndKeyword(@ApiParam(value = "作者id") String authorId,
                                                                                    @ApiParam(value = "关键词") String keyword,
                                                                                    @ModelAttribute DocumentQueryCommon documentQueryCommon) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByAuthorIdAndKeyword(authorId, keyword, documentQueryCommon));
    }

    /**
     * 根据标签检索应用内的所有文档
     * @param tags 标签
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据标签检索文档")
    @GetMapping("/queryDocumentByTags")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByTags(@ApiParam(value = "标签") String tags,
                                                                      @ModelAttribute DocumentQueryCommon documentQueryCommon) {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByTags(tags, documentQueryCommon));
    }
}
