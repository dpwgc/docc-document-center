package com.dpwgc.document.center.app.command.document;

import com.dpwgc.document.center.sdk.model.document.*;

import java.io.IOException;

public interface DocumentCommandService {

    String createDocument(CreateDocumentCommand createDocumentCommand) throws IOException;

    Boolean updateDocument(UpdateDocumentCommand updateDocumentCommand) throws IOException;
}
