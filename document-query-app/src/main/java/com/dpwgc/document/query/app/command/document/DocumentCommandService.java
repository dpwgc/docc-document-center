package com.dpwgc.document.query.app.command.document;

import com.dpwgc.document.query.sdk.model.document.CreateDocumentCommand;
import com.dpwgc.document.query.sdk.model.document.DeleteDocumentCommand;
import com.dpwgc.document.query.sdk.model.document.UpdateDocumentCommand;

public interface DocumentCommandService {

    String createDocument(CreateDocumentCommand createDocumentCommand);

    Boolean updateDocument(UpdateDocumentCommand updateDocumentCommand);

    Boolean deleteDocument(DeleteDocumentCommand deleteDocumentCommand);
}
