/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.biz.service.logic.exception;

import ${package}.common.service.response.soap.base.WSResult;

/**
 * Reason:	 Logic 异常设置. 
 * 
 * @author chenlg
 * @version $Id: LogicHandleError.java, v 0.1 2014年3月12日 下午3:15:44 chenlg Exp $
 * @since    JDK 1.7
 */
public class LogicHandleError {

    protected <T extends WSResult> T handleParameterError(T result, Exception e, String message) {
        result.setError(WSResult.PARAMETER_ERROR, message);
        return result;
    }

    protected <T extends WSResult> T handleParameterError(T result, Exception e) {
        result.setError(WSResult.PARAMETER_ERROR, e.getMessage());
        return result;
    }

    protected <T extends WSResult> T handleGeneralError(T result, Exception e) {
        result.setDefaultError();
        return result;
    }
}
