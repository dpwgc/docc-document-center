package com.dpwgc.document.center.app.command.document;

import com.dpwgc.document.center.sdk.model.document.*;

public interface DocumentCommandService {

    String createDocument(CreateDocumentCommand createDocumentCommand);

    Boolean updateDocumentMain(UpdateDocumentMainCommand updateDocumentMainCommand);

    Boolean updateDocumentAuthLevel(UpdateDocumentAuthLevelCommand updateDocumentAuthLevelCommand);

    Boolean updateDocumentType(UpdateDocumentTypeCommand updateDocumentTypeCommand);

    Boolean updateDocumentScore(UpdateDocumentScoreCommand updateDocumentScoreCommand);

    Boolean updateDocumentLove(UpdateDocumentLoveCommand updateDocumentLoveCommand);

    Boolean updateDocumentLike(UpdateDocumentLikeCommand updateDocumentLikeCommand);

    Boolean updateDocumentRead(UpdateDocumentReadCommand updateDocumentReadCommand);

    Boolean deleteDocument(DeleteDocumentCommand deleteDocumentCommand);
}
