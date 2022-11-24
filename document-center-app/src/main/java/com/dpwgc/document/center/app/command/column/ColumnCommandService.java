package com.dpwgc.document.center.app.command.column;

import com.dpwgc.document.center.sdk.model.column.CreateColumnCommand;
import com.dpwgc.document.center.sdk.model.column.UpdateColumnCommand;

public interface ColumnCommandService {

    String createColumn(CreateColumnCommand createColumnCommand);

    Boolean updateColumn(UpdateColumnCommand updateColumnCommand);
}
