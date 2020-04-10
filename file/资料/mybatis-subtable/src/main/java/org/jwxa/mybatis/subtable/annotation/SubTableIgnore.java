package org.jwxa.mybatis.subtable.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 标识的方法忽略分表解析
 * @author janjan
 *
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface SubTableIgnore {
}
