package com.dpwgc.document.center.ui.interfaces.document;

import com.dpwgc.document.center.app.command.document.DocumentCommandService;
import com.dpwgc.document.center.app.query.document.DocumentQueryService;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.document.DocumentQuery;
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
     * 更新文档（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档（匹配ES主键id）")
    @PostMapping("/updateDocument")
    public ResultDTO<Boolean> updateDocument(@RequestBody UpdateDocumentCommand updateDocumentCommand) {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocument(updateDocumentCommand));
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
    public ResultDTO<PageBase<List<DocumentDTO>>> searchDocument(@ModelAttribute DocumentQuery documentQuery) {
        return ResultDTO.getSuccessResult(documentQueryService.searchDocument(documentQuery));
    }

    /**
     * 文档数据聚合统计
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "文档数据聚合统计")
    @GetMapping("/aggregationsDocument")
    public ResultDTO<AggregationsDTO> aggregationsDocument(@ModelAttribute AggregationsQuery aggregationsQuery) {
        return ResultDTO.getSuccessResult(documentQueryService.aggregationsDocument(aggregationsQuery));
    }
}
