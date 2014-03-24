/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.biz.service.logic.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ${package}.common.service.facade.jaxrs.AccountJaxRsService;
import ${package}.common.service.response.jaxrs.dto.UserJaxrsDTO;
import ${package}.core.domain.account.AccountDomain;
import ${package}.core.persistant.po.account.UserPO;

/**
 * Reason:	Jaxrs的WebService接口定义类.
 * <p>实现借口是为了描述具体的接口调用信息</p>
 * 
 * @author chenlg
 * @version $Id: AccountJaxRsServiceImpl.java, v 0.1 2014年3月12日 下午3:26:21 chenlg Exp $
 * @since    JDK 1.7
 */
@Path("/user")
public class AccountJaxRsServiceImpl implements AccountJaxRsService {

    private static Logger logger = LoggerFactory.getLogger(AccountJaxRsServiceImpl.class);

    @Autowired
    @Qualifier("accountDomain")
    private AccountDomain accountDomain;

    @GET
    @Path("/{id}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public UserJaxrsDTO getAsXml(@PathParam("id") Integer id) {
        UserPO userPo = accountDomain.getUser(id);
        if (userPo == null) {
            String message = "用户不存在(id:" + id + ")";
            if (logger.isInfoEnabled())
                logger.info(message);

            throw buildException(Status.NOT_FOUND, message);
        }
        return bindDTO(userPo);
    }

    @GET
    @Path("/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public UserJaxrsDTO getAsJson(@PathParam("id") Integer id) {
        UserPO userPo = accountDomain.getUser(id);
        if (userPo == null) {
            String message = "用户不存在(id:" + id + ")";
            if (logger.isInfoEnabled())
                logger.info(message);

            throw buildException(Status.NOT_FOUND, message);
        }
        return bindDTO(userPo);
    }

    private UserJaxrsDTO bindDTO(UserPO user) {
        UserJaxrsDTO userDto = new UserJaxrsDTO();

        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    private WebApplicationException buildException(Status status, String message) {
        return new WebApplicationException(Response.status(status).entity(message)
            .type(MediaType.TEXT_PLAIN).build());
    }

}
