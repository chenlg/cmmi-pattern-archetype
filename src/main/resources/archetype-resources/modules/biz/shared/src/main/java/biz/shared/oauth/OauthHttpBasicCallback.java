/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.biz.shared.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

/**
 * Reason:	   示例 实际使用需要修改设用的方式
 *  <p>1.最简单的方式是使用Http Basic Auth，就是WSS4J的UsernameToken实现方式，
 *  优点是简单易用，缺点是每次都会在MESSAGE里面传密码，安全性低。</p>
 *  <p> 2.Transport level(传输层内)的实现Https。CXF samples里面有一个例子wsdl_first_https， 很详细的讲了怎么使用。</p>
 *  <p>3.对MESSAGE进行加密和签名(encryption and signing)，请参考官方教程。</p>
 * 
 * @author chenlg
 * @version $Id: OauthHttpBasicCallback.java, v 0.1 2014年2月28日 上午10:40:52 chenlg Exp $
 * @since    JDK 1.7
 */
public class OauthHttpBasicCallback implements CallbackHandler {

    Map<String, String> user = new HashMap<String, String>();
    {
        user.put("admin", "123");
        user.put("su", "123");
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback wpc = (WSPasswordCallback) callbacks[0];
        if (!user.containsKey(wpc.getIdentifier())) {
            throw new SecurityException("No Permission!");
        }

        int usage = wpc.getUsage();
        /* 
         * 此处特别注意:: 
         * WSPasswordCallback 的passwordType属性和password 属性都为null， 
         * 你只能获得用户名（identifier）， 
         * 一般这里的逻辑是使用这个用户名到数据库中查询其密码， 
         * 然后再设置到password 属性，WSS4J 会自动比较客户端传来的值和你设置的这个值。 
         * 你可能会问为什么这里CXF 不把客户端提交的密码传入让我们在ServerPasswordCallbackHandler 中比较呢？ 
         * 这是因为客户端提交过来的密码在SOAP 消息中已经被加密为MD5 的字符串， 
         * 如果我们要在回调方法中作比较，那么第一步要做的就是把服务端准备好的密码加密为MD5 字符串， 
         * 由于MD5 算法参数不同结果也会有差别，另外，这样的工作CXF 替我们完成不是更简单吗？ 
         */
        if (usage == WSPasswordCallback.USERNAME_TOKEN) {// 密钥方式USERNAME_TOKEN
            /*
             * set the password for client's keystore.keyPassword   
             * 这里的值必须和客户端设的值相同,从cxf2.4.x后校验方式改为cxf内部实现校验，不必自己比较password是否相同;   
             * 请参考：http://cxf.apache.org/docs/24-migration-guide.html的Runtime   
             * Changes片段   
             */
            wpc.setPassword(user.get(wpc.getIdentifier()));//如果包含用户名,就设置该用户名正确密码,由CXF验证密码  
            /*
             * PS 如果和客户端不同将抛出org.apache.ws.security.WSSecurityException:   
             *The  security token could not be authenticated or   authorized异常，服务端会认为客户端为非法调用 
             */
        } else if (usage == WSPasswordCallback.SIGNATURE) {// 密钥方式SIGNATURE   
            /*
             * set the password for client's keystore.keyPassword   
             * 这里的值必须和客户端设的值相同,从cxf2.4.x后校验方式改为cxf内部实现校验，不必自己比较password是否相同;   
             * 请参考：http://cxf.apache.org/docs/24-migration-guide.html的Runtime   
             * Changes片段   
             */
            wpc.setPassword(user.get(wpc.getIdentifier()));//如果包含用户名,就设置该用户名正确密码,由CXF验证密码  
            /*
             * PS 如果和客户端不同将抛出org.apache.ws.security.WSSecurityException:   
             *The  security token could not be authenticated or   authorized异常，服务端会认为客户端为非法调用 
             */
        }
        
        String username = wpc.getIdentifier();
        String password = wpc.getPassword();
        System.out.println("userName:" + username + " password:" + password);
    }

}
