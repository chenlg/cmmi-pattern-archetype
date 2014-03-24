/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.common.service.response.soap.base;

import javax.xml.bind.annotation.XmlType;

import ${package}.common.service.response.WsConstants;


/**
 * 创建某个对象返回的 通用IdResult.
 * 
 * @author chenlg
 */
@XmlType(name = "IdResult", namespace = WsConstants.NS)
public class IdResult extends WSResult {
	private Integer id;

	public IdResult() {
	}

	public IdResult(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
