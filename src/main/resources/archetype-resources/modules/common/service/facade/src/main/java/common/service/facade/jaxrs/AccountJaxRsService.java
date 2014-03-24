/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.common.service.facade.jaxrs;

import javax.ws.rs.PathParam;

import ${package}.common.service.response.jaxrs.dto.UserJaxrsDTO;
import ${package}.common.shared.annotation.Description;

/**
 * Reason:	 该接口主要用户描述接口具体调用.对与其他无效果. 
 * 
 * @author chenlg
 * @version $Id: AccountJaxRsService.java, v 0.1 2014年2月25日 下午2:58:26 chenlg Exp $
 * @since    JDK 1.7
 */
@Description
public interface AccountJaxRsService {
    
    /**
     *  访问实例: 
     *  <code>http://localhost:8080/${projectId}/cxf/jaxrs/user/1.xml</code>
     * 
     * @param id
     * @return
     */
    public UserJaxrsDTO getAsXml(@PathParam("id") Integer id);

    /**
     * 访问实例: 
     * <code> http://localhost:8080/${projectId}/cxf/jaxrs/user/1.json</code>
     * 
     * @param id
     * @return
     */
    public UserJaxrsDTO getAsJson(@PathParam("id") Integer id) ;
}
