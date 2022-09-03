package com.dpwgc.document.center.app.command.document.impl;

import com.dpwgc.document.center.app.assembler.DocumentAssembler;
import com.dpwgc.document.center.app.command.document.DocumentCommandService;
import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.domain.document.DocumentFactory;
import com.dpwgc.document.center.domain.document.DocumentRepository;
import com.dpwgc.document.center.domain.tag.TagFactory;
import com.dpwgc.document.center.domain.tag.TagRepository;
import com.dpwgc.document.center.infrastructure.util.IdGenUtil;
import com.dpwgc.document.center.infrastructure.util.JsonUtil;
import com.dpwgc.document.center.infrastructure.util.LogUtil;
import com.dpwgc.document.center.sdk.model.document.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;

@Service
public class DocumentCommandServiceImpl implements DocumentCommandService {

    @Resource
    IdGenUtil idGenUtil;

    @Resource
    DocumentRepository documentRepository;

    @Resource
    TagRepository tagRepository;

    @Override
    public String createDocument(CreateDocumentCommand createDocumentCommand) throws IOException {

        DocumentFactory documentFactory = new DocumentFactory();
        Document document = documentFactory.create(
                createDocumentCommand.getAppId(),
                createDocumentCommand.getCategoryId(),
                createDocumentCommand.getAuthorId(),
                idGenUtil.nextIdString(),
                createDocumentCommand.getTitle(),
                createDocumentCommand.getContent(),
                JsonUtil.toJson(createDocumentCommand.getTags()),
                createDocumentCommand.getSummary(),
                createDocumentCommand.getAuthLevel(),
                createDocumentCommand.getScore(),
                createDocumentCommand.getFilter(),
                createDocumentCommand.getAttr(),
                createDocumentCommand.getType()
        );

        //将该文档的标签更新至DB
        for (String tag : createDocumentCommand.getTags()) {
            TagFactory tagFactory = new TagFactory();
            if (!tagRepository.createTag(tagFactory.create(createDocumentCommand.getAppId(), tag))) {
                //写入失败
                LogUtil.error("createTag error","tagRepository.createTag() return false","tag");
                return null;
            }
        }

        //创建文档
        return documentRepository.createDocument(document);
    }

    @Override
    public Boolean updateDocument(UpdateDocumentCommand updateDocumentCommand) throws IOException {

        Document document = DocumentAssembler.INSTANCE.assembleDocumentFromUpdate(updateDocumentCommand);

        //更新时间
        document.setUpdateTime(System.currentTimeMillis());

        return documentRepository.updateDocument(document);
    }
}
