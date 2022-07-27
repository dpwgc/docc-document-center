package com.dpwgc.document.query.app.command.document.impl;

import com.dpwgc.document.query.app.command.document.DocumentCommandService;
import com.dpwgc.document.query.domain.document.Document;
import com.dpwgc.document.query.domain.document.DocumentFactory;
import com.dpwgc.document.query.domain.document.DocumentRepository;
import com.dpwgc.document.query.infrastructure.util.IdGenUtil;
import com.dpwgc.document.query.sdk.model.document.CreateDocumentCommand;
import com.dpwgc.document.query.sdk.model.document.DeleteDocumentCommand;
import com.dpwgc.document.query.sdk.model.document.UpdateDocumentCommand;
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
                createDocumentCommand.getType()
        );

        return documentRepository.createDocument(document);
    }

    @Override
    public Boolean updateDocument(UpdateDocumentCommand updateDocumentCommand) {
        return false;
    }

    @Override
    public Boolean deleteDocument(DeleteDocumentCommand deleteDocumentCommand) {
        return documentRepository.deleteDocument(deleteDocumentCommand.getId());
    }
}
