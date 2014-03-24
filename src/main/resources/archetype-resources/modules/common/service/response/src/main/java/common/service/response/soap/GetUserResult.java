/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.common.service.response.soap;

import javax.xml.bind.annotation.XmlType;

import ${package}.common.service.response.WsConstants;
import ${package}.common.service.response.soap.base.WSResult;
import ${package}.common.service.response.soap.dto.UserSoapDTO;

/**
 * Reason:   单用户返回类. 
 * 
 * @author chenlg
 * @version $Id: SearchUserResult.java, v 0.1 2014年3月18日 上午11:17:43 chenlg Exp $
 * @since    JDK 1.7
 */
@XmlType(name = "GetUserResult", namespace = WsConstants.NS)
public class GetUserResult extends WSResult {
	private UserSoapDTO user;

	public UserSoapDTO getUser() {
		return user;
	}

	public void setUser(UserSoapDTO user) {
		this.user = user;
	}
}
