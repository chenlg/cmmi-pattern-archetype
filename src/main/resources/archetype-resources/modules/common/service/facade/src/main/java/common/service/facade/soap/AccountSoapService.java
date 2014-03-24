/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.common.service.facade.soap;

import javax.jws.WebParam;
import javax.jws.WebService;

import ${package}.common.service.response.WsConstants;
import ${package}.common.service.response.soap.GetUserResult;
import ${package}.common.service.response.soap.base.IdResult;
import ${package}.common.service.response.soap.dto.UserSoapDTO;

/**
 * Reason:	  * JAX-WS2.0的WebService接口定义类.
 * 
 * 使用JAX-WS2.0 annotation设置WSDL中的定义.
 * 使用WSResult及其子类类包裹返回结果.
 * 使用DTO传输对象隔绝系统内部领域对象的修改对外系统的影响.
 * 
 * @author chenlg
 * @version $Id: AccountSoapService.java, v 0.1 2014年2月19日 下午3:37:17 chenlg Exp $
 * @since    JDK 1.7
 */
//name 指明wsdl中<wsdl:portType>元素的名称
@WebService(name = "AccountService", targetNamespace = WsConstants.NS)
public interface AccountSoapService {
    /**
     * 获取用户信息.
     * 
     * @param id
     * @return GetUserResult
     */
    GetUserResult getUser(@WebParam(name = "id") Integer id);

    /**
     * 新建用户.
     * 
     * @param user
     * @return IdResult
     */
    IdResult createUser(@WebParam(name = "user") UserSoapDTO user);
}
