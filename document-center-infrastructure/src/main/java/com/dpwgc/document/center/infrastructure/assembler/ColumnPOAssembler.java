package com.dpwgc.document.center.infrastructure.assembler;

import com.dpwgc.document.center.domain.column.Column;
import com.dpwgc.document.center.infrastructure.dal.column.entity.ColumnPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ColumnPOAssembler {

    ColumnPOAssembler INSTANCE = Mappers.getMapper(ColumnPOAssembler.class);

    Column assembleColumn(ColumnPO columnPO);

    ColumnPO assembleColumnPO(Column column);
}
