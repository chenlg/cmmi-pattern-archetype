/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.common.service.response.soap;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import ${package}.common.service.response.WsConstants;
import ${package}.common.service.response.soap.base.WSResult;
import ${package}.common.service.response.soap.dto.UserSoapDTO;

/**
 * Reason:	 多用户收索返回类. 
 * 
 * @author chenlg
 * @version $Id: SearchUserResult.java, v 0.1 2014年3月18日 上午11:17:43 chenlg Exp $
 * @since    JDK 1.7
 */
@XmlType(name = "SearchUserResult", namespace = WsConstants.NS)
public class SearchUserResult extends WSResult {

    private List<UserSoapDTO> userList;

    public SearchUserResult() {
    }

    public SearchUserResult(List<UserSoapDTO> userList) {
        this.userList = userList;
    }

    @XmlElementWrapper(name = "userList")
    @XmlElement(name = "user")
    public List<UserSoapDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserSoapDTO> userList) {
        this.userList = userList;
    }
}
