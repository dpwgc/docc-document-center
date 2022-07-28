package com.dpwgc.document.center.app.command.document;

import com.dpwgc.document.center.sdk.model.document.CreateDocumentCommand;
import com.dpwgc.document.center.sdk.model.document.DeleteDocumentCommand;
import com.dpwgc.document.center.sdk.model.document.UpdateDocumentCommand;

public interface DocumentCommandService {

    String createDocument(CreateDocumentCommand createDocumentCommand);

    Boolean updateDocument(UpdateDocumentCommand updateDocumentCommand);

    Boolean deleteDocument(DeleteDocumentCommand deleteDocumentCommand);
}
