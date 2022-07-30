package com.dpwgc.document.center.ui.interfaces.document;

import co.elastic.clients.elasticsearch._types.SortOrder;
import com.dpwgc.document.center.app.command.document.DocumentCommandService;
import com.dpwgc.document.center.app.query.document.DocumentQueryService;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.document.*;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 文档-接口
 */
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
    @PostMapping("createDocument")
    public ResultDTO<Object> createDocument(@RequestBody CreateDocumentCommand createDocumentCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.createDocument(createDocumentCommand));
    }

    /**
     * 更新文档主体内容（匹配ES主键id）
     */
    @PostMapping("updateDocumentMain")
    public ResultDTO<Object> updateDocumentMain(@RequestBody UpdateDocumentMainCommand updateDocumentMainCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentMain(updateDocumentMainCommand));
    }

    /**
     * 更新文档权限等级（匹配ES主键id）
     */
    @PostMapping("updateDocumentAuthLevel")
    public ResultDTO<Object> updateDocumentAuthLevel(@RequestBody UpdateDocumentAuthLevelCommand updateDocumentAuthLevelCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentAuthLevel(updateDocumentAuthLevelCommand));
    }

    /**
     * 更新文档类型（匹配ES主键id）
     */
    @PostMapping("updateDocumentType")
    public ResultDTO<Object> updateDocumentType(@RequestBody UpdateDocumentTypeCommand updateDocumentTypeCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentType(updateDocumentTypeCommand));
    }

    /**
     * 更新文档推荐评分（匹配ES主键id）
     */
    @PostMapping("updateDocumentScore")
    public ResultDTO<Object> updateDocumentScore(@RequestBody UpdateDocumentScoreCommand updateDocumentScoreCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentScore(updateDocumentScoreCommand));
    }

    /**
     * 更新文档收藏数（匹配ES主键id）
     */
    @PostMapping("updateDocumentLove")
    public ResultDTO<Object> updateDocumentLove(@RequestBody UpdateDocumentLoveCommand updateDocumentLoveCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentLove(updateDocumentLoveCommand));
    }

    /**
     * 更新文档点赞数（匹配ES主键id）
     */
    @PostMapping("updateDocumentLike")
    public ResultDTO<Object> updateDocumentLike(@RequestBody UpdateDocumentLikeCommand updateDocumentLikeCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentLike(updateDocumentLikeCommand));
    }

    /**
     * 更新文档阅读数（匹配ES主键id）
     */
    @PostMapping("updateDocumentRead")
    public ResultDTO<Object> updateDocumentRead(@RequestBody UpdateDocumentReadCommand updateDocumentReadCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocumentRead(updateDocumentReadCommand));
    }

    /**
     * 删除文档（匹配ES主键id）
     */
    @PostMapping("deleteDocument")
    public ResultDTO<Object> deleteDocument(@RequestBody DeleteDocumentCommand deleteDocumentCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.deleteDocument(deleteDocumentCommand));
    }

    /**
     * 根据关键词检索文档
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @GetMapping("queryDocumentByKeyword")
    public ResultDTO<Object> queryDocumentByKeyword(String keyword, Integer authLevel, String sortField, String sortOrder, Integer pageIndex, Integer pageSize) {
        SortOrder so = null;
        if (sortOrder.equals("Asc")) {
            so = SortOrder.Asc;
        } else {
            so = SortOrder.Desc;
        }
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByKeyword(keyword, authLevel, sortField, so, pageIndex, pageSize));
    }

    /**
     * 根据分类id检索文档
     * @param categoryId 分类id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @GetMapping("queryDocumentByCategoryId")
    public ResultDTO<Object> queryDocumentByCategoryId(String categoryId, Integer authLevel, String sortField, String sortOrder, Integer pageIndex, Integer pageSize) {
        SortOrder so = null;
        if (sortOrder.equals("Asc")) {
            so = SortOrder.Asc;
        } else {
            so = SortOrder.Desc;
        }
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByCategoryId(categoryId, authLevel, sortField, so, pageIndex, pageSize));
    }

    /**
     * 根据分类id与关键词检索文档
     * @param categoryId 分类id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @GetMapping("queryDocumentByCategoryIdAndKeyword")
    public ResultDTO<Object> queryDocumentByCategoryIdAndKeyword(String categoryId, String keyword, Integer authLevel, String sortField, String sortOrder, Integer pageIndex, Integer pageSize) {
        SortOrder so = null;
        if (sortOrder.equals("Asc")) {
            so = SortOrder.Asc;
        } else {
            so = SortOrder.Desc;
        }
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByCategoryIdAndKeyword(categoryId, keyword, authLevel, sortField, so, pageIndex, pageSize));
    }

    /**
     * 根据分类id与文档类型type检索文档
     * @param categoryId 分类id
     * @param type 文档类型
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @GetMapping("queryDocumentByCategoryIdAndType")
    public ResultDTO<Object> queryDocumentByCategoryIdAndType(String categoryId, Integer type, Integer authLevel, String sortField, String sortOrder, Integer pageIndex, Integer pageSize) {
        SortOrder so = null;
        if (sortOrder.equals("Asc")) {
            so = SortOrder.Asc;
        } else {
            so = SortOrder.Desc;
        }
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByCategoryIdAndType(categoryId, type, authLevel, sortField, so, pageIndex, pageSize));
    }

    /**
     * 根据作者id检索文档
     * @param authorId 作者id
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @GetMapping("queryDocumentByAuthorId")
    public ResultDTO<Object> queryDocumentByAuthorId(String authorId, Integer authLevel, String sortField, String sortOrder, Integer pageIndex, Integer pageSize) {
        SortOrder so = null;
        if (sortOrder.equals("Asc")) {
            so = SortOrder.Asc;
        } else {
            so = SortOrder.Desc;
        }
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByAuthorId(authorId, authLevel, sortField, so, pageIndex, pageSize));
    }

    /**
     * 根据作者id与关键词检索文档
     * @param authorId 作者id
     * @param keyword 关键词
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @GetMapping("queryDocumentByAuthorIdAndKeyword")
    public ResultDTO<Object> queryDocumentByAuthorIdAndKeyword(String authorId, String keyword, Integer authLevel, String sortField, String sortOrder, Integer pageIndex, Integer pageSize) {
        SortOrder so = null;
        if (sortOrder.equals("Asc")) {
            so = SortOrder.Asc;
        } else {
            so = SortOrder.Desc;
        }
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByAuthorIdAndKeyword(authorId, keyword, authLevel, sortField, so, pageIndex, pageSize));
    }

    /**
     * 根据标签检索文档
     * @param tags 标签
     * @param authLevel 查看该文档所需要的权限级别（用户权限必须>=文档权限，才会返回该文档数据）
     * @param sortField 选用排序字段 例：update_time
     * @param sortOrder 排序规则 SortOrder.Desc/SortOrder.Asc
     * @param pageIndex 分页开始
     * @param pageSize 分页大小
     * @return ResultDTO<Object>
     */
    @GetMapping("queryDocumentByTags")
    public ResultDTO<Object> queryDocumentByTags(String tags, Integer authLevel, String sortField, String sortOrder, Integer pageIndex, Integer pageSize) {
        SortOrder so = null;
        if (sortOrder.equals("Asc")) {
            so = SortOrder.Asc;
        } else {
            so = SortOrder.Desc;
        }
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByTags(tags, authLevel, sortField, so, pageIndex, pageSize));
    }
}
