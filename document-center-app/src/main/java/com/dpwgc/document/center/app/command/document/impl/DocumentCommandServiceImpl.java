package com.dpwgc.document.center.app.command.document.impl;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class DocumentCommandServiceImpl implements DocumentCommandService {

    @Resource
    IdGenUtil idGenUtil;

    @Resource
    DocumentRepository documentRepository;

    @Resource
    TagRepository tagRepository;

    @Override
    public String createDocument(CreateDocumentCommand createDocumentCommand) {

        DocumentFactory documentFactory = new DocumentFactory();
        Document document = documentFactory.create(
                createDocumentCommand.getAppId(),
                createDocumentCommand.getCategoryId(),
                createDocumentCommand.getAuthorId(),
                idGenUtil.nextIdString(),
                createDocumentCommand.getTitle(),
                createDocumentCommand.getContent(),
                createDocumentCommand.getTags(),
                createDocumentCommand.getSummary(),
                createDocumentCommand.getAuthLevel(),
                createDocumentCommand.getScore(),
                createDocumentCommand.getLove(),
                createDocumentCommand.getLike(),
                createDocumentCommand.getRead(),
                createDocumentCommand.getType()
        );

        List<String> tags = null;
        try {
            //用json工具解析出tag列表：["tag1","tag2","tag3"]
            tags = JsonUtil.fromJson(createDocumentCommand.getTags(),List.class);
        } catch (JsonProcessingException e) {
            LogUtil.error(e.toString());
            return null;
        }

        //将该文档的标签更新至DB
        for (String tag : tags) {
            TagFactory tagFactory = new TagFactory();
            tagRepository.createTag(tagFactory.create(createDocumentCommand.getAppId(), tag));
        }

        //创建文档
        return documentRepository.createDocument(document);
    }

    @Override
    public Boolean updateDocumentMain(UpdateDocumentMainCommand updateDocumentMainCommand) {

        Document document = new Document();
        document.setId(updateDocumentMainCommand.getId());
        document.setCategoryId(updateDocumentMainCommand.getCategoryId());
        document.setTitle(updateDocumentMainCommand.getTitle());
        document.setContent(updateDocumentMainCommand.getContent());
        document.setSummary(updateDocumentMainCommand.getSummary());
        document.setTags(updateDocumentMainCommand.getTags());

        //更新时间
        document.setUpdateTime(System.currentTimeMillis());

        return documentRepository.updateDocument(document);
    }

    @Override
    public Boolean updateDocumentAuthLevel(UpdateDocumentAuthLevelCommand updateDocumentAuthLevelCommand) {

        Document document = new Document();
        document.setId(updateDocumentAuthLevelCommand.getId());
        document.setAuthLevel(updateDocumentAuthLevelCommand.getAuthLevel());

        //更新时间
        document.setUpdateTime(System.currentTimeMillis());

        return documentRepository.updateDocument(document);
    }

    @Override
    public Boolean updateDocumentType(UpdateDocumentTypeCommand updateDocumentTypeCommand) {

        Document document = new Document();
        document.setId(updateDocumentTypeCommand.getId());
        document.setType(updateDocumentTypeCommand.getType());

        //更新时间
        document.setUpdateTime(System.currentTimeMillis());

        return documentRepository.updateDocument(document);
    }

    @Override
    public Boolean updateDocumentScore(UpdateDocumentScoreCommand updateDocumentScoreCommand) {

        Document document = new Document();
        document.setId(updateDocumentScoreCommand.getId());
        document.setScore(updateDocumentScoreCommand.getScore());

        //更新时间
        document.setUpdateTime(System.currentTimeMillis());

        return documentRepository.updateDocument(document);
    }

    @Override
    public Boolean updateDocumentLove(UpdateDocumentLoveCommand updateDocumentLoveCommand) {

        Document document = new Document();
        document.setId(updateDocumentLoveCommand.getId());
        document.setLove(updateDocumentLoveCommand.getLove());

        //更新时间
        document.setUpdateTime(System.currentTimeMillis());

        return documentRepository.updateDocument(document);
    }

    @Override
    public Boolean updateDocumentLike(UpdateDocumentLikeCommand updateDocumentLikeCommand) {

        Document document = new Document();
        document.setId(updateDocumentLikeCommand.getId());
        document.setLike(updateDocumentLikeCommand.getLike());

        //更新时间
        document.setUpdateTime(System.currentTimeMillis());

        return documentRepository.updateDocument(document);
    }

    @Override
    public Boolean updateDocumentRead(UpdateDocumentReadCommand updateDocumentReadCommand) {

        Document document = new Document();
        document.setId(updateDocumentReadCommand.getId());
        document.setRead(updateDocumentReadCommand.getRead());

        //更新时间
        document.setUpdateTime(System.currentTimeMillis());

        return documentRepository.updateDocument(document);
    }

    @Override
    public Boolean deleteDocument(DeleteDocumentCommand deleteDocumentCommand) {

        Document document = new Document();
        document.setId(deleteDocumentCommand.getId());
        document.setStatus(0);
        document.setUpdateTime(System.currentTimeMillis());

        return documentRepository.deleteDocument(document);
    }
}
