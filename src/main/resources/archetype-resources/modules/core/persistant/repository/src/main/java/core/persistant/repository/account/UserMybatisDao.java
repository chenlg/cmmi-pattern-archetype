/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.core.persistant.repository.account;

import java.util.List;
import java.util.Map;

import ${package}.core.persistant.po.account.UserPO;
import ${package}.core.persistant.repository.MyBatisRepository;

/**
 * Reason:	 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author chenlg
 * @version $Id: UserMybatisDao.java, v 0.1 2014年2月19日 上午11:06:51 chenlg Exp $
 * @since    JDK 1.7
 * @see
 */
@MyBatisRepository
public interface UserMybatisDao {

    /**
     * 根据userId 获取用户记录对象.
     */
    UserPO get(Integer userId);

    /**
     * 根据条件查询用户记录集合.
     */
    List<UserPO> search(Map<String, Object> parameters);

    /**
     * 根据用户对象新增记录.
     */
    void save(UserPO userPO);

    /**
     * 根据userId 删除用户记录.
     */
    void delete(Integer userId);
    
}
