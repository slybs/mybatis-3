package org.jwxa.mybatis.subtable.converter;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.jwxa.mybatis.subtable.annotation.SubTable;
import org.jwxa.mybatis.subtable.annotation.SubTableIgnore;
import org.jwxa.mybatis.subtable.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.deparser.StatementDeParser;

/**
 * SQL转换器抽象类
 * @author tenglege
 *
 */
public abstract class AbstractSqlConverter implements SqlConverter {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 做真正的转换
	 * @param statement
	 * @param params
	 * @return
	 */
	public abstract Statement doConvert(final Statement statement, final Object params);
	
	// 当前分表策略
	private Strategy currentStrategy;
	// 分表策略缓存
	private static final ConcurrentHashMap<String, Strategy> cache = new ConcurrentHashMap<String, Strategy>();
	
	/**
	 * 是否包含定义的注解
	 * @param mapperId Mapper id
	 * @return
	 */
	private boolean hasAnnotation(String mapperId) {
		if(StringUtils.isBlank(mapperId)) {
			return false;
		}
		logger.debug("[mapperId] " + mapperId);
		// 类名
		String className = StringUtils.substring(mapperId, 0, mapperId.lastIndexOf("."));
		// 方法名
		String methodName = StringUtils.substring(mapperId, mapperId.lastIndexOf(".") + 1);
		logger.debug("[className -> methodName] " + className + " -> " + methodName);
		try {
			Class<?> clazz = Class.forName(className);
			SubTable subTable = clazz.getAnnotation(SubTable.class);
			currentStrategy = cache.get(className);
			if(subTable != null && currentStrategy == null) {
				currentStrategy = subTable.strategyClass().newInstance();
				cache.put(className, currentStrategy);
			}
			Method method = null;
			for(Method m : clazz.getDeclaredMethods()) {
				if(m.getName().equals(methodName)) {
					method = m;
				}
			}
			SubTableIgnore ignore = method != null ? method.getAnnotation(SubTableIgnore.class) : null;
			return subTable != null && ignore == null;
		} catch (ClassNotFoundException e) {
			logger.error("[hasAnnotation ClassNotFoundException]", e);
		} catch (InstantiationException e) {
			logger.error("[hasAnnotation InstantiationException]", e);
		} catch (IllegalAccessException e) {
			logger.error("[hasAnnotation IllegalAccessException]", e);
		} catch (SecurityException e) {
			logger.error("[hasAnnotation SecurityException]", e);
		}
		return false;
	}
	
	/**
	 * Statement对象反解析成sql语句
	 * @param statement
	 * @return
	 */
	private String doDeParse(Statement statement) {
		if(statement == null) return null;
		StatementDeParser deParser = new StatementDeParser(new StringBuilder());
		statement.accept(deParser);
		return deParser.getBuffer().toString();
	}
	
	/**
	 * 获取分表后的TableName
	 * @param baseTableName 基础表
	 * @param params 传入参数
	 * @return
	 */
	protected String getFinalTable(String baseTableName, Object params) {
		Validate.notBlank(baseTableName);
		if(currentStrategy != null) {
			logger.debug("[currentStrategy ClassName] " + currentStrategy.getClass().getName());
			return currentStrategy.getFinalTable(baseTableName, params);
		}
		return baseTableName;
	}
	
	@Override
	public String convert(Statement statement, Object params, String mapperId) {
		if(!hasAnnotation(mapperId) || statement == null) {
			return null;
		}
		return doDeParse(doConvert(statement, params));
	}
}
