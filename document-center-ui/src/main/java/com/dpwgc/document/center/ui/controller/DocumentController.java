package com.dpwgc.document.center.ui.controller;

import com.dpwgc.document.center.app.command.document.DocumentCommandService;
import com.dpwgc.document.center.app.query.document.DocumentQueryService;
import com.dpwgc.document.center.sdk.base.PageBase;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.document.DocumentQuery;
import com.dpwgc.document.center.sdk.model.document.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
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
    @MutationMapping
    public ResultDTO<String> createDocument(@RequestBody @Validated CreateDocumentCommand createDocumentCommand, BindingResult bindingResult) throws IOException {
        return ResultDTO.getSuccessResult(documentCommandService.createDocument(createDocumentCommand));
    }

    /**
     * 更新文档（匹配ES主键id）
     */
    @ApiOperation(value = "更新文档（匹配ES主键id）")
    @PostMapping("/updateDocument")
    @MutationMapping
    public ResultDTO<Boolean> updateDocument(@RequestBody @Validated UpdateDocumentCommand updateDocumentCommand, BindingResult bindingResult) throws IOException {
        return ResultDTO.getSuccessResult(documentCommandService.updateDocument(updateDocumentCommand));
    }

    /**
     * 根据ES主键id查询文档
     * （获取文档详情）
     * @param id ES主键id
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据ES主键id查询文档")
    @GetMapping("/queryDocumentByESId")
    @QueryMapping
    ResultDTO<DocumentDTO> queryDocumentByESId(@ApiParam(value = "ES主键id") String id) throws IOException {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByESId(id));
    }

    /**
     * 根据ES主键id集合查询文档
     * （可以用这接口来查询用户的收藏文档列表）
     * @param idList ES主键id集合（逗号间隔：xxx,xxx,xxx）
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "根据ES主键id集合查询文档（逗号间隔：xxx,xxx,xxx）")
    @GetMapping("/queryDocumentByESIdList")
    @QueryMapping
    ResultDTO<List<DocumentDTO>> queryDocumentByESIdList(@ApiParam(value = "ES主键id集合") String idList) throws IOException {
        return ResultDTO.getSuccessResult(documentQueryService.queryDocumentByESIdList(Arrays.asList(idList.split(","))));
    }

    /**
     * 文档检索
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "文档检索")
    @GetMapping("/searchDocument")
    @QueryMapping
    public ResultDTO<PageBase<List<DocumentDTO>>> searchDocument(@ModelAttribute DocumentQuery documentQuery) throws IOException {
        //pageIndex格式转换
        documentQuery.pageIndexConvert();
        return ResultDTO.getSuccessResult(documentQueryService.searchDocument(documentQuery));
    }

    /**
     * 文档数据聚合统计
     * @return ResultDTO<Object>
     */
    @ApiOperation(value = "文档数据聚合统计")
    @GetMapping("/aggregationsDocument")
    @QueryMapping
    public ResultDTO<AggregationDTO> aggregationDocument(@ModelAttribute AggregationQuery aggregationQuery) throws IOException {
        return ResultDTO.getSuccessResult(documentQueryService.aggregationDocument(aggregationQuery));
    }
}
