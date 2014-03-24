/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.core.domain.account;


import java.util.List;
import java.util.Map;

import ${package}.core.persistant.po.account.UserPO;

/**
 * Reason:	 业务领域层. 
 * 
 * @author chenlg
 * @version $Id: AccountDomain.java, v 0.1 2014年2月19日 下午2:39:58 chenlg Exp $
 * @since    JDK 1.7
 * @see
 */
public interface AccountDomain {
    
    /**
     * 根据userId 获取用户记录对象.
     */
    UserPO getUser(Integer userId);

    /**
     * 根据条件查询用户记录集合.
     */
    List<UserPO> searchUsers(Map<String, Object> parameters);

    /**
     * 根据用户对象新增用户记录.
     */
    void saveUser(UserPO userPO);

    /**
     * 根据userId 删除用户记录.
     */
    void deleteUser(Integer userId);

}
