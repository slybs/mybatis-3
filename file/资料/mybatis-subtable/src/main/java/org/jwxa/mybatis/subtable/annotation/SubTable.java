package org.jwxa.mybatis.subtable.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.jwxa.mybatis.subtable.strategy.Strategy;

/**
 * 标识Mapper是分表对象
 * @author tenglege
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface SubTable {
	
	/**
	 * 分表策略class
	 * @return
	 */
	Class<? extends Strategy> strategyClass();
	
}
