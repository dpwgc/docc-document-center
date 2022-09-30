package com.dpwgc.document.center.app.assembler;

import com.dpwgc.document.center.domain.column.Column;
import com.dpwgc.document.center.infrastructure.dal.column.entity.ColumnPO;
import com.dpwgc.document.center.sdk.model.column.ColumnDTO;
import com.dpwgc.document.center.sdk.model.column.UpdateColumnCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ColumnAssembler {

    ColumnAssembler INSTANCE = Mappers.getMapper(ColumnAssembler.class);

    ColumnDTO assembleColumnDTO(Column column);

    Column assembleColumnFromUpdate(UpdateColumnCommand updateColumnCommand);

    ColumnDTO assembleColumnDTO(ColumnPO columnPO);
}
