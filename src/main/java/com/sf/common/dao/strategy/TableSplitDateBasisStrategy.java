package com.sf.common.dao.strategy;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.sf.common.config.TableSplitConfigCenter;

@Component
public class TableSplitDateBasisStrategy implements TableSplitStrategy<Date> {
  
//  private static final String TABLE_SPLIT = "tableSplit";
  
  @Override
  public String getTargetTableName(Date splitBasis,String tableName) {
    return TableSplitConfigCenter.Instance.getShardName(tableName,new Date());
  }
  @Override
  public boolean accept(TableSplitStrategyEnum tableSplitStrategyEnum) {
    return tableSplitStrategyEnum == TableSplitStrategyEnum.DateBasis;
  }
  
}
