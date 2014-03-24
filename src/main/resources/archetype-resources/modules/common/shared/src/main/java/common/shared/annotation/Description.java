/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.common.shared.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Reason:	 表述描述信息.无任何其它作用.
 */
//说明该注解将被包含在javadoc中  
@Documented
//编译时存在运行时不加载
@Retention(RetentionPolicy.CLASS)  
//这个注解可以是类注解，包注解，也可以是方法的注解  
@Target({ ElementType.TYPE, ElementType.PACKAGE,ElementType.METHOD }) 
public @interface Description {

}
