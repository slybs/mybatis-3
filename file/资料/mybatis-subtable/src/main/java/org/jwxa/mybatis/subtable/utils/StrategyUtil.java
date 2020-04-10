package org.jwxa.mybatis.subtable.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 分表策略工具类
 * @author tenglege
 *
 */
public class StrategyUtil {

	/**
	 * 获取字符串md5对于的long值
	 * @param 	flag String类型标识
	 * @return 	flag对于的long值
	 */
	public static long getMd5HashFlag(String flag) {
		if(StringUtils.isBlank(flag)) {
			return -1;
		}
		// 删除空格
		String flagTmp = StringUtils.deleteWhitespace(flag);
		// 字符串16进制MD5
		flagTmp = DigestUtils.md5Hex(flagTmp.getBytes());
		// 截取后3位
		flagTmp = StringUtils.substring(flagTmp, flagTmp.length() - 3);
		// 转换为16进制
		return Long.parseLong(flagTmp, 16);
	}
	
	/**
	 * 获取散列分表名
	 * @param table 	基本表名
	 * @param separator 分隔符（基础表和散列中间的分隔符）
	 * @param flag 		散列依据（分表字段的值，一般为主键）
	 * @param n			散列大小（分表的数量）
	 * @return
	 */
	public static String getHashTable(String table, String separator, long flag, int n) {
		if (flag < 0 || n <= 0) {
			return table;
		}
		// 分表后缀
		long suffix = flag % n;
		// 后缀长度
		int suffixLength = Long.toString(suffix).length();
		// 散列长度
		int nLength = Integer.toString(n).length() - 1;
		// 基础表
		StringBuilder tableBuilder = new StringBuilder(table).append(separator);
		// 循环添加前缀：0
		for (int i = 0; i < (nLength - suffixLength); ++i)
			tableBuilder.append(0);
		// 添加后缀
		tableBuilder.append(suffix);
		return tableBuilder.toString();
	}
	
	public static String getHashTable(String table, String separator, String flag, int n) {
		return getHashTable(table, separator, getMd5HashFlag(flag), n);
	}
	
}
