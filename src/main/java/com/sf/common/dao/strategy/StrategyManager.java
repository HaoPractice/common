package com.sf.common.dao.strategy;

import java.util.List;

import com.google.common.collect.Lists;

public enum StrategyManager {
  ;
  @SuppressWarnings("rawtypes")
  public static final List<TableSplitStrategy> tableSplitStrategies = Lists.newArrayList();

  public static final <SplitBasis> String getTargetTableName(
      TableSplitStrategyEnum tableSplitStrategyEnum, SplitBasis splitBasis, String tableName) {

    for (@SuppressWarnings("rawtypes")
    TableSplitStrategy tableSplitStrategy : tableSplitStrategies) {
      if (tableSplitStrategy.accept(tableSplitStrategyEnum)) {
        @SuppressWarnings("unchecked")
        String targetTableName = tableSplitStrategy.getTargetTableName(splitBasis, tableName);
        return targetTableName;
      }
    }

    return null;

  }
}
