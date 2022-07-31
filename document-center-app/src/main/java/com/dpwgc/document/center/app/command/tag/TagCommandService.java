package com.dpwgc.document.center.app.command.tag;

import com.dpwgc.document.center.sdk.model.tag.DeleteTagCommand;
import com.dpwgc.document.center.sdk.model.tag.UpdateTagNumberCommand;

public interface TagCommandService {

    Boolean updateTagNumber(UpdateTagNumberCommand updateTagNumberCommand);

    Boolean deleteTag(DeleteTagCommand deleteTagCommand);
}
