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
     * 返回应用内的文档列表
     * @param appId 应用id
     * @param authLevel 查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 Desc/Asc
     * @param pageIndex 分页起始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "返回应用内的文档列表")
    @GetMapping("/listDocument")
    public ResultDTO<PageBase<List<DocumentDTO>>> listDocument(@ApiParam(value = "应用id") String appId,
                                                               @ApiParam(value = "查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）") Integer authLevel,
                                                               @ApiParam(value = "选用排序字段 例：update_time") String sortField,
                                                               @ApiParam(value = "排序规则 Desc/Asc") String sortOrder,
                                                               @ApiParam(value = "分页起始") Integer pageIndex,
                                                               @ApiParam(value = "分页大小") Integer pageSize) {

        DocumentQueryCommon documentQueryCommon = new DocumentQueryCommon();
        documentQueryCommon.create(appId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        return ResultDTO.getSuccessResult(documentQueryService.listDocument(documentQueryCommon));
    }

    /**
     * 根据关键词检索应用内的所有文档
     * @param appId 应用id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 Desc/Asc
     * @param pageIndex 分页起始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据关键词检索应用内的所有文档")
    @GetMapping("/queryDocumentByKeyword")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByKeyword(@ApiParam(value = "应用id") String appId,
                                                                         @ApiParam(value = "关键词") String keyword,
                                                                         @ApiParam(value = "查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）") Integer authLevel,
                                                                         @ApiParam(value = "选用排序字段 例：update_time") String sortField,
                                                                         @ApiParam(value = "排序规则 Desc/Asc") String sortOrder,
                                                                         @ApiParam(value = "分页起始") Integer pageIndex,
                                                                         @ApiParam(value = "分页大小") Integer pageSize) {

        DocumentQueryCommon documentQueryCommon = new DocumentQueryCommon();
        documentQueryCommon.create(appId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByKeyword(keyword, documentQueryCommon));
    }

    /**
     * 根据分类id检索文档
     * @param appId 应用id
     * @param categoryId 分类id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 Desc/Asc
     * @param pageIndex 分页起始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据分类id检索文档")
    @GetMapping("/queryDocumentByCategoryId")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByCategoryId(@ApiParam(value = "应用id") String appId,
                                                                            @ApiParam(value = "分类id") String categoryId,
                                                                            @ApiParam(value = "查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）") Integer authLevel,
                                                                            @ApiParam(value = "选用排序字段 例：update_time") String sortField,
                                                                            @ApiParam(value = "排序规则 Desc/Asc") String sortOrder,
                                                                            @ApiParam(value = "分页起始") Integer pageIndex,
                                                                            @ApiParam(value = "分页大小") Integer pageSize) {

        DocumentQueryCommon documentQueryCommon = new DocumentQueryCommon();
        documentQueryCommon.create(appId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByCategoryId(categoryId, documentQueryCommon));
    }

    /**
     * 根据分类id与关键词检索文档
     * @param appId 应用id
     * @param categoryId 分类id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 Desc/Asc
     * @param pageIndex 分页起始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据分类id与关键词检索文档")
    @GetMapping("/queryDocumentByCategoryIdAndKeyword")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByCategoryIdAndKeyword(@ApiParam(value = "应用id") String appId,
                                                                                      @ApiParam(value = "分类id") String categoryId,
                                                                                      @ApiParam(value = "关键词") String keyword,
                                                                                      @ApiParam(value = "查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）") Integer authLevel,
                                                                                      @ApiParam(value = "选用排序字段 例：update_time") String sortField,
                                                                                      @ApiParam(value = "排序规则 Desc/Asc") String sortOrder,
                                                                                      @ApiParam(value = "分页起始") Integer pageIndex,
                                                                                      @ApiParam(value = "分页大小") Integer pageSize) {

        DocumentQueryCommon documentQueryCommon = new DocumentQueryCommon();
        documentQueryCommon.create(appId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByCategoryIdAndKeyword(categoryId, keyword, documentQueryCommon));
    }

    /**
     * 根据分类id与文档类型type检索文档
     * @param appId 应用id
     * @param categoryId 分类id
     * @param type 文档类型
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 Desc/Asc
     * @param pageIndex 分页起始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据分类id与文档类型type检索文档")
    @GetMapping("/queryDocumentByCategoryIdAndType")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByCategoryIdAndType(@ApiParam(value = "应用id") String appId,
                                                                                   @ApiParam(value = "分类id") String categoryId,
                                                                                   @ApiParam(value = "文档类型") Integer type,
                                                                                   @ApiParam(value = "查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）") Integer authLevel,
                                                                                   @ApiParam(value = "选用排序字段 例：update_time") String sortField,
                                                                                   @ApiParam(value = "排序规则 Desc/Asc") String sortOrder,
                                                                                   @ApiParam(value = "分页起始") Integer pageIndex,
                                                                                   @ApiParam(value = "分页大小") Integer pageSize) {

        DocumentQueryCommon documentQueryCommon = new DocumentQueryCommon();
        documentQueryCommon.create(appId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByCategoryIdAndType(categoryId, type, documentQueryCommon));
    }

    /**
     * 根据作者id检索文档
     * @param appId 应用id
     * @param authorId 作者id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 Desc/Asc
     * @param pageIndex 分页起始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据作者id检索文档")
    @GetMapping("/queryDocumentByAuthorId")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByAuthorId(@ApiParam(value = "应用id") String appId,
                                                                          @ApiParam(value = "作者id") String authorId,
                                                                          @ApiParam(value = "查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）") Integer authLevel,
                                                                          @ApiParam(value = "选用排序字段 例：update_time") String sortField,
                                                                          @ApiParam(value = "排序规则 Desc/Asc") String sortOrder,
                                                                          @ApiParam(value = "分页起始") Integer pageIndex,
                                                                          @ApiParam(value = "分页大小") Integer pageSize) {

        DocumentQueryCommon documentQueryCommon = new DocumentQueryCommon();
        documentQueryCommon.create(appId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByAuthorId(authorId, documentQueryCommon));
    }

    /**
     * 根据作者id与关键词检索文档
     * @param appId 应用id
     * @param authorId 作者id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 Desc/Asc
     * @param pageIndex 分页起始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据作者id与关键词检索文档")
    @GetMapping("/queryDocumentByAuthorIdAndKeyword")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByAuthorIdAndKeyword(@ApiParam(value = "应用id") String appId,
                                                                                    @ApiParam(value = "作者id") String authorId,
                                                                                    @ApiParam(value = "关键词") String keyword,
                                                                                    @ApiParam(value = "查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）") Integer authLevel,
                                                                                    @ApiParam(value = "选用排序字段 例：update_time") String sortField,
                                                                                    @ApiParam(value = "排序规则 Desc/Asc") String sortOrder,
                                                                                    @ApiParam(value = "分页起始") Integer pageIndex,
                                                                                    @ApiParam(value = "分页大小") Integer pageSize) {

        DocumentQueryCommon documentQueryCommon = new DocumentQueryCommon();
        documentQueryCommon.create(appId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByAuthorIdAndKeyword(authorId, keyword, documentQueryCommon));
    }

    /**
     * 根据标签检索应用内的所有文档
     * @param appId 应用id
     * @param tags 标签
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 Desc/Asc
     * @param pageIndex 分页起始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据标签检索文档")
    @GetMapping("/queryDocumentByTags")
    public ResultDTO<PageBase<List<DocumentDTO>>> queryDocumentByTags(@ApiParam(value = "应用id") String appId,
                                                                      @ApiParam(value = "标签") String tags,
                                                                      @ApiParam(value = "查看该文档所需的权限级别（用户权限必须>=文档权限，才会返回该文档数据）") Integer authLevel,
                                                                      @ApiParam(value = "选用排序字段 例：update_time") String sortField,
                                                                      @ApiParam(value = "排序规则 Desc/Asc") String sortOrder,
                                                                      @ApiParam(value = "分页起始") Integer pageIndex,
                                                                      @ApiParam(value = "分页大小") Integer pageSize) {

        DocumentQueryCommon documentQueryCommon = new DocumentQueryCommon();
        documentQueryCommon.create(appId, authLevel, sortField, sortOrder, pageIndex, pageSize);
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByTags(tags, documentQueryCommon));
    }
}
