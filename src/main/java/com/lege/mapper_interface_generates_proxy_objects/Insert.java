package com.lege.mapper_interface_generates_proxy_objects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tenglege
 * @description todo
 * @date 2020/3/15 16:07
 * 用来跳过验证
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Insert {
}
