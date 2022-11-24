package com.dpwgc.document.center.infrastructure.repository.column;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.document.center.domain.column.Column;
import com.dpwgc.document.center.domain.column.ColumnRepository;
import com.dpwgc.document.center.infrastructure.assembler.ColumnPOAssembler;
import com.dpwgc.document.center.infrastructure.dal.column.entity.ColumnPO;
import com.dpwgc.document.center.infrastructure.dal.column.mapper.ColumnMapper;
import com.dpwgc.document.center.sdk.base.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ColumnRepositoryImpl implements ColumnRepository {

    @Value("${optimistic.update.retry}")
    private int retry;

    @Resource
    ColumnMapper columnMapper;

    public String createColumn(Column column) {
        if (columnMapper.insert(ColumnPOAssembler.INSTANCE.assembleColumnPO(column))>0) {
            return column.getColumnId();
        }
        return null;
    }

    public Boolean updateColumn(Column column) {
        QueryWrapper<ColumnPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",column.getAppId());
        queryWrapper.eq("column_id",column.getColumnId());
        queryWrapper.eq("status", Status.NORMAL);

        // 判断是否存在 + 版本号获取
        queryWrapper.select("version");
        ColumnPO po = columnMapper.selectOne(queryWrapper);
        if (po == null) {
            return false;
        }

        ColumnPO columnPO = ColumnPOAssembler.INSTANCE.assembleColumnPO(column);
        //带上版本号（乐观锁更新）
        columnPO.setVersion(po.getVersion());
        //更新时间
        columnPO.setUpdateTime(System.currentTimeMillis());

        for (int i=0;i<retry;i++) {
            //如果成功
            if (columnMapper.update(columnPO, queryWrapper) > 0) {
                return true;
            }
            //如果失败，重新获取版本，再次更新
            po = columnMapper.selectOne(queryWrapper);
            columnPO.setVersion(po.getVersion());
        }
        return false;
    }
}
