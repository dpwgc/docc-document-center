package com.dpwgc.document.center.app.command.column.impl;

import com.dpwgc.document.center.app.assembler.ColumnAssembler;
import com.dpwgc.document.center.app.command.column.ColumnCommandService;
import com.dpwgc.document.center.domain.column.Column;
import com.dpwgc.document.center.domain.column.ColumnFactory;
import com.dpwgc.document.center.domain.column.ColumnRepository;
import com.dpwgc.document.center.infrastructure.util.IdGenUtil;
import com.dpwgc.document.center.sdk.model.column.CreateColumnCommand;
import com.dpwgc.document.center.sdk.model.column.UpdateColumnCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ColumnCommandServiceImpl implements ColumnCommandService {

    @Resource
    ColumnRepository columnRepository;

    @Resource
    IdGenUtil idGenUtil;

    public String createColumn(CreateColumnCommand createColumnCommand) {
        ColumnFactory columnFactory = new ColumnFactory();
        Column column = columnFactory.create(
                createColumnCommand.getAppId(),
                idGenUtil.nextIdString(),
                createColumnCommand.getAuthorId(),
                createColumnCommand.getColumnName(),
                createColumnCommand.getDetail(),
                createColumnCommand.getExtra(),
                createColumnCommand.getScore(),
                createColumnCommand.getAttr(),
                createColumnCommand.getType()
        );
        return columnRepository.createColumn(column);
    }

    public Boolean updateColumn(UpdateColumnCommand updateColumnCommand) {
        Column column = ColumnAssembler.INSTANCE.assembleColumnFromUpdate(updateColumnCommand);
        return columnRepository.updateColumn(column);
    }
}
