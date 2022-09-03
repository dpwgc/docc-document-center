package com.dpwgc.document.center.app.command.document;

import com.dpwgc.document.center.sdk.model.document.*;

public interface DocumentCommandService {

    String createDocument(CreateDocumentCommand createDocumentCommand);

    Boolean updateDocument(UpdateDocumentCommand updateDocumentCommand);
}
