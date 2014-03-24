/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
/**
 * Reason:   业务逻辑共享区. 
 * 
 *     <p>功能描述:  逻辑区常用功能提取封装  </p>
 *     
 *     <span>日志拦截 pointcut expression表达式解析</span>
 *     <table>
 *          <tr>
 *              <td>任意公共方法的执行：</td>
 *              <td>execution(public * *(..))</td>
 *          </tr>
 *          <tr>
 *              <td>任何一个以“set”开始的方法的执行：</td>
 *              <td>execution(* set*(..))</td>
 *          </tr>
 *          <tr>
 *              <td>AccountService 接口的任意方法的执行：</td>
 *              <td>execution(* com.xyz.service.AccountService.*(..))</td>
 *          </tr>
 *          <tr>
 *              <td>定义在service包里的任意方法的执行：</td>
 *              <td>execution(* com.xyz.service.*.*(..))</td>
 *          </tr>
 *          <tr>
 *              <td>定义在service包和所有子包里的任意类的任意方法的执行：</td>
 *              <td>execution(* com.xyz.service..*.*(..))</td>
 *          </tr>
 *          <tr>
 *              <td>定义在pointcutexp包和所有子包里的JoinPointObjP2类的任意方法的执行：</td>
 *              <td>execution(* com.test.spring.aop.pointcutexp..JoinPointObjP2.*(..))")</td>
 *          </tr>
 *          
 *          <tr>
 *              <td>pointcutexp包里的任意类.</td>
 *              <td>within(com.test.spring.aop.pointcutexp.*)</td>
 *          </tr>
 *          <tr>
 *              <td>pointcutexp包和所有子包里的任意类.</td>
 *              <td>within(com.test.spring.aop.pointcutexp..*)</td>
 *          </tr>
 *          <tr>
 *              <td>实现了Intf接口的所有类,如果Intf不是接口,限定Intf单个类.</td>
 *              <td>this(com.test.spring.aop.pointcutexp.Intf)</td>
 *          </tr>
 *          
 *          <tr>
 *              <td>带有@Transactional标注的所有类的任意方法.</td>
 *              <td>@within(org.springframework.transaction.annotation.Transactional)
 *                      @target(org.springframework.transaction.annotation.Transactional)
 *              </td>
 *          </tr>
 *           <tr>
 *              <td>带有@Transactional标注的任意方法.</td>
 *              <td>@annotation(org.springframework.transaction.annotation.Transactional)
 *              </td>
 *          </tr>
 *          
 *          <tr>
 *              <td>参数带有@Transactional标注的方法.</td>
 *              <td>@args(org.springframework.transaction.annotation.Transactional)
 *              </td>
 *          </tr>
 *          <tr>
 *              <td>参数为String类型(运行是决定)的方法.</td>
 *              <td>args(String)
 *              </td>
 *          </tr>
 *     </table>
 */
package ${package}.biz.shared;