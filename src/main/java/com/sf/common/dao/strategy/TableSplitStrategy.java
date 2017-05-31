package com.sf.common.dao.strategy;

public interface TableSplitStrategy<SplitBasis>{
  String getTargetTableName(SplitBasis splitBasis,String tableName);
  boolean accept(TableSplitStrategyEnum tableSplitStrategyEnum);
}
