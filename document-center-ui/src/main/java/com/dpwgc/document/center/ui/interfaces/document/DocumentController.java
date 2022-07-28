package com.dpwgc.document.center.ui.interfaces.document;

import co.elastic.clients.elasticsearch._types.SortOrder;
import com.dpwgc.document.center.app.command.document.DocumentCommandService;
import com.dpwgc.document.center.sdk.base.ResultDTO;
import com.dpwgc.document.center.sdk.model.document.CreateDocumentCommand;
import com.dpwgc.document.center.sdk.model.document.DeleteDocumentCommand;
import com.dpwgc.document.center.sdk.model.document.UpdateDocumentCommand;
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

    @PostMapping("createDocument")
    public ResultDTO<Object> createDocument(@RequestBody CreateDocumentCommand createDocumentCommand) {
        return ResultDTO.getSuccessResult(null);
    }

    @PostMapping("updateDocument")
    public ResultDTO<Object> updateDocument(@RequestBody UpdateDocumentCommand updateDocumentCommand) {
        return ResultDTO.getSuccessResult(null);
    }

    @PostMapping("deleteDocument")
    public ResultDTO<Object> deleteDocument(@RequestBody DeleteDocumentCommand deleteDocumentCommand) {
        return ResultDTO.getSuccessResult(null);
    }

    @GetMapping("queryDocumentByKeyword")
    public ResultDTO<Object> queryDocumentByKeyword(String keyword, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        return ResultDTO.getSuccessResult(null);
    }

    @GetMapping("queryDocumentByCategoryId")
    public ResultDTO<Object> queryDocumentByCategoryId(String categoryId, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        return ResultDTO.getSuccessResult(null);
    }

    @GetMapping("queryDocumentByCategoryIdAndType")
    public ResultDTO<Object> queryDocumentByCategoryIdAndType(String categoryId, Integer type, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        return ResultDTO.getSuccessResult(null);
    }

    @GetMapping("queryDocumentByTags")
    public ResultDTO<Object> queryDocumentByTags(String tags, String sortField, SortOrder sortOrder, Integer pageIndex, Integer pageSize) {
        return ResultDTO.getSuccessResult(null);
    }
}
