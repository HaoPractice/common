package com.sf.common.config;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

import com.google.common.collect.Maps;

public enum TableSplitConfigCenter {
  Instance;

  public static class SplitTableConfig{
    String originalTable;
    String shardTable;
    Date splitDate;
    public String getOriginalTable() {
      return originalTable;
    }
    public String getShardTable() {
      return shardTable;
    }
    public Date getSplitDate() {
      return splitDate;
    }
    
  };
  //  private static final String TABLE_SPLIT = "tableSplit";
  private final Map<String ,NavigableMap<Date,String>> tableNameMapping = Maps.newHashMap();
  public void updateTableName(Map<String ,NavigableMap<Date,String>> tableNameMapping){
    for (Entry<String, NavigableMap<Date, String>> entry : tableNameMapping.entrySet()) {
      String key = entry.getKey();
      if(this.tableNameMapping.containsKey(key)){
        this.tableNameMapping.get(key).putAll(entry.getValue());
      }else{
        this.tableNameMapping.put(key, entry.getValue());
      }
    }
  }
  
  public String getTargetTableName(Date splitBasis,String tableName) {
    NavigableMap<Date, String> navigableMap = tableNameMapping.get(tableName);
    if(navigableMap == null){
      return tableName;
    }
    String targetTable = navigableMap.get(splitBasis);
    if (targetTable == null) {
      return tableName;
    }
    return targetTable;
  }

  /**
   * 获取根据日期分表的表名
   * 
   * @param tableName
   * @param key
   * @return
   */
  
  public String getShardName(String tableName,Date key) {
    
    return null;
  }
  

}
