/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
/**
 * Reason:  公共注解描述..
 *     <p>层次描述类无效.</p>
 *     
 *     <p>整体产品可以单独封装注解式描述,暂时放入common下属shared 层</p> 
 *     
 * // 说明该注解将被包含在javadoc中  
 * @Documented  
 * // 这个注解可以是类注解，也可以是方法的注解  
 * @Target({ ElementType.TYPE, ElementType.METHOD })  
 * // 定义的这个注解是注解会在class字节码文件中存在，在运行时可以通过反射获取到。  
 * @Retention(RetentionPolicy.RUNTIME)  
 * // 子类可以继承父类中的该注解  
 * @Inherited  
 * public @interface MyAnnotation {  
 *   // 为注解定义一个方法即为注解定义了一个元素，返回的默认值为hell world  
 *  public String name() default "hello world";  
 * }  
 */
package ${package}.common.shared.annotation;