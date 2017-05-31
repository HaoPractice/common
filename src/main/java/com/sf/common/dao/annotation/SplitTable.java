/**
 * 
 */
package com.sf.common.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sf.common.dao.strategy.TableSplitStrategyEnum;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/**
 * @author hao19
 *
 */
public @interface SplitTable {
  TableSplitStrategyEnum splitStrategy() default TableSplitStrategyEnum.DateBasis;
  String paramName() default "tradingDate";
  String tableName() ;
}
