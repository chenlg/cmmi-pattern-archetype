/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.core.persistant.po.account;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Reason:	 用户持久对象. 
 * 
 * @author chenlg
 * @version $Id: UserPO.java, v 0.1 2014年2月19日 上午10:46:03 chenlg Exp $
 * @since    JDK 1.7
 * @see
 */
public class UserPO implements Serializable {

    /**  */
    private static final long serialVersionUID = 4013995615036633324L;

    /**
     * 用户主键
     */
    private Integer           userId;

    /**
     * 用户名
     */
    private String            userName;

    /**
     * 登录名
     */
    private String            userAccount;

    /**
     * 登录密码
     */
    private String            userPwd;

    /**
     * 加密值
     */
    private String            salt;

    /**
     * 用户邮箱
     */
    private String            userEmail;

    /**
     * 用户状态
     */
    private boolean           status;

    /**
     * 权限
     */
    private String            permissions;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
    
    @NotBlank
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
