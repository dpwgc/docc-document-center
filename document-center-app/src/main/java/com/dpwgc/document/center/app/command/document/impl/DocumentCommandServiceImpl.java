package com.dpwgc.document.center.app.command.document.impl;

import com.dpwgc.document.center.app.command.document.DocumentCommandService;
import com.dpwgc.document.center.domain.document.Document;
import com.dpwgc.document.center.domain.document.DocumentFactory;
import com.dpwgc.document.center.domain.document.DocumentRepository;
import com.dpwgc.document.center.infrastructure.util.IdGenUtil;
import com.dpwgc.document.center.sdk.model.document.CreateDocumentCommand;
import com.dpwgc.document.center.sdk.model.document.DeleteDocumentCommand;
import com.dpwgc.document.center.sdk.model.document.UpdateDocumentCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DocumentCommandServiceImpl implements DocumentCommandService {

    @Resource
    IdGenUtil idGenUtil;

    @Resource
    DocumentRepository documentRepository;

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
                createDocumentCommand.getType(),
                System.currentTimeMillis(),
                System.currentTimeMillis()
        );
        return documentRepository.createDocument(document);
    }

    @Override
    public Boolean updateDocument(UpdateDocumentCommand updateDocumentCommand) {

        Document document = documentRepository.queryDocumentById(updateDocumentCommand.getId());
        document.setCategoryId(updateDocumentCommand.getCategoryId());
        document.setTitle(updateDocumentCommand.getTitle());
        document.setContent(updateDocumentCommand.getContent());
        document.setSummary(updateDocumentCommand.getSummary());
        document.setTags(updateDocumentCommand.getTags());
        document.setAuthLevel(updateDocumentCommand.getAuthLevel());
        document.setScore(updateDocumentCommand.getScore());
        document.setType(updateDocumentCommand.getType());

        return documentRepository.updateDocument(document);
    }

    @Override
    public Boolean deleteDocument(DeleteDocumentCommand deleteDocumentCommand) {
        return documentRepository.deleteDocument(deleteDocumentCommand.getId());
    }
}
