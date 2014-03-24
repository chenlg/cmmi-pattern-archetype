/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.core.domain.account.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ${package}.core.domain.account.AccountDomain;
import ${package}.core.persistant.po.account.UserPO;
import ${package}.core.persistant.repository.account.UserMybatisDao;

/**
 * Reason:	业务领域层具体实现.
 * 
 * @author chenlg
 * @version $Id: AccountDomainImpl.java, v 0.1 2014年2月19日 下午2:46:22 chenlg Exp $
 * @since    JDK 1.7
 * @see
 */
@Component("accountDomain")
@Transactional(readOnly = true)
public class AccountDomainImpl implements AccountDomain {

    @Autowired
    private UserMybatisDao userDao;

    /**
     * @see ${package}.core.domain.account.AccountDomain#getUser(java.lang.Integer)
     */
    @Override
    public UserPO getUser(Integer userId) {
        return userDao.get(userId);
    }

    /**
     * @see ${package}.core.domain.account.AccountDomain#searchUsers(java.util.Map)
     */
    @Override
    public List<UserPO> searchUsers(Map<String, Object> parameters) {
        return userDao.search(parameters);
    }

    /**
     * @see ${package}.core.domain.account.AccountDomain#saveUser(${package}.core.persistant.po.account.UserPO)
     */
    @Override
    @Transactional(readOnly = false)
    public void saveUser(UserPO userPO) {
        userDao.save(userPO);
    }

    /**
     * @see ${package}.core.domain.account.AccountDomain#deleteUser(java.lang.Integer)
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteUser(Integer userId) {
        userDao.delete(userId);
    }

}
