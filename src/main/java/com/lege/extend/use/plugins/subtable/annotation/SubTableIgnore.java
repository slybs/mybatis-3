package com.lege.extend.use.plugins.subtable.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

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
