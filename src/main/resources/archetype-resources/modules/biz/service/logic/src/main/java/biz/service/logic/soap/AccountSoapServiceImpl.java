/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.biz.service.logic.soap;

import javax.jws.WebService;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;

import ${package}.biz.service.logic.exception.LogicHandleError;
import ${package}.biz.service.logic.exception.ServiceExceptions;
import ${package}.common.service.facade.soap.AccountSoapService;
import ${package}.common.service.response.WsConstants;
import ${package}.common.service.response.soap.GetUserResult;
import ${package}.common.service.response.soap.base.IdResult;
import ${package}.common.service.response.soap.dto.UserSoapDTO;
import ${package}.common.shared.annotation.AspectLogger;
import ${package}.common.utils.beanvalidator.BeanValidators;
import ${package}.core.domain.account.AccountDomain;
import ${package}.core.persistant.po.account.UserPO;

/**
 * Reason:	  WebService服务端实现类.
 * 
 *  演示，直接调用了Dao层.客户端实现见功能测试用例.
 * 
 * @author chenlg
 * @version $Id: AccountSoapServiceImpl.java, v 0.1 2014年2月20日 上午10:10:23 chenlg Exp $
 * @since    JDK 1.7
 */
//serviceName指明WSDL中<wsdl:service>与<wsdl:binding>元素的名称, endpointInterface属性指向Interface类全称.
@WebService(serviceName = "AccountService", endpointInterface = "${package}.common.service.facade.soap.AccountSoapService", targetNamespace = WsConstants.NS)
public class AccountSoapServiceImpl extends LogicHandleError implements AccountSoapService {

    private static Logger logger = LoggerFactory.getLogger(AccountSoapServiceImpl.class);

    @Autowired
    @Qualifier("accountDomain")
    private AccountDomain accountDomain;

    @Autowired
    @Qualifier("validator")
    private Validator     validator;

    @Override
    @AspectLogger(value = "用户查询", discover = true)
    public GetUserResult getUser(Integer id) {
        if (logger.isInfoEnabled())
            logger.info("测试获取用户信息!");

        GetUserResult result = new GetUserResult();
        try {

            Validate.notNull(id, "id参数为空");

            UserPO userPo = accountDomain.getUser(id);

            Validate.notNull(userPo, "用户不存在(id:" + id + ")");
            UserSoapDTO userDto = new UserSoapDTO();

            BeanUtils.copyProperties(userPo, userDto);

            result.setUser(userDto);
            return result;
        } catch (IllegalArgumentException e) {
            if (logger.isErrorEnabled())
                logger.error(e.getMessage());

            return handleParameterError(result, e);
        } catch (RuntimeException e) {
            if (logger.isErrorEnabled())
                logger.error(e.getMessage());

            return handleGeneralError(result, e);
        }
    }

    @Override
    public IdResult createUser(UserSoapDTO userDto) {
        if (logger.isInfoEnabled())
            logger.info("测试创建用户!");
        IdResult result = new IdResult();
        UserPO userPo = null;
        try {
            Validate.notNull(userDto, "用户参数为空");

            userPo = new UserPO();
            BeanUtils.copyProperties(userDto, userPo);
            //         User userEntity = BeanMapper.map(user, User.class);
            BeanValidators.validateWithException(validator, userDto);
            BeanUtils.copyProperties(userDto, userPo);
            accountDomain.saveUser(userPo);

            return new IdResult(userPo.getUserId());
        } catch (ConstraintViolationException e) {
            String message = StringUtils.join(
                BeanValidators.extractPropertyAndMessageAsList(e, " "), "\n");
            return handleParameterError(result, e, message);
        } catch (RuntimeException e) {
            if (ServiceExceptions.isCausedBy(e, DuplicateKeyException.class)) {
                String message = "新建用户参数存在唯一性冲突(用户:" + userPo + ")";
                if (logger.isErrorEnabled())
                    logger.error(message, e.getMessage());
                return handleParameterError(result, e, message);
            } else {
                if (logger.isErrorEnabled())
                    logger.error(e.getMessage());

                return handleParameterError(result, e);
            }
        }
    }

}
