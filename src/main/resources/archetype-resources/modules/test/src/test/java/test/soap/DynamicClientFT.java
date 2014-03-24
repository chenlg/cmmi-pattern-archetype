/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.test.soap;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.junit.Test;

import ${package}.common.service.facade.soap.AccountSoapService;
import ${package}.common.service.response.soap.GetUserResult;
import ${package}.test.unit.BaseUnitTestCase;
import ${package}.test.unit.context.Profiles;

/**
 * Reason:	  AccountService Web服务的功能测试, 测试主要的接口调用.
 * 
 * 以用JAXWS的API, 根据AccountWebService接口自行创建.
 * 
 * <p>该测试未证书认证。如果需要自行添加证书</p>
 * <p>问题，回调类型转换异常</p>
 * 
 * @author chenlg
 */
public class DynamicClientFT extends BaseUnitTestCase {

    public AccountSoapService creatClient() {
        String address = Profiles.baseUrl + "/cxf/soap/accountservice";
        
        SpringBusFactory bf = new SpringBusFactory();
        URL busFile = DynamicClientFT.class.getResource("dynamic-client.xml");
        try {
            Bus bus = bf.createBus(busFile.toString());
            BusFactory.setDefaultBus(bus);
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        Map<String, Object> outProps = new HashMap<String, Object>();
        outProps.put("action", "Timestamp Encrypt Signature");
        outProps.put("passwordType", "PasswordDigest");
        outProps.put("passwordCallbackClass", "${package}.test.soap.oauth.ClientOauthCertCallback");
        outProps.put("user", "admin");
        outProps.put("passwordCallbackRef", "passwordCallback");

        outProps.put("encryptionPropFile", "etc/Client_Encrypt.properties");
        outProps.put("encryptionUser", "serverkey");
        outProps.put("signaturePropFile", "etc/Client_Sign.properties");
        outProps.put("signatureUser", "clientkey");

        Map<String, Object> inProps = new HashMap<String, Object>();
        inProps.put("action", "Timestamp Encrypt Signature");
        inProps.put("passwordCallbackClass", "${package}.test.soap.oauth.ClientOauthCertCallback");
        inProps.put("decryptionPropFile", "etc/Client_Sign.properties");
        inProps.put("signaturePropFile", "etc/Client_Encrypt.properties");

        
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setAddress(address);
        proxyFactory.setServiceClass(AccountSoapService.class);
        
        proxyFactory.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
        proxyFactory.getInInterceptors().add(new WSS4JInInterceptor(inProps));
        

        AccountSoapService accountWebServiceProxy = (AccountSoapService) proxyFactory.create();

        // (可选)演示重新设定endpoint address.
//        ((BindingProvider) accountWebServiceProxy).getRequestContext().put(
//            BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);


        // (可选)演示重新设定Timeout时间
//        Client client = ClientProxy.getClient(accountWebServiceProxy);
//        HTTPConduit conduit = (HTTPConduit) client.getConduit();
//        HTTPClientPolicy policy = conduit.getClient();
//        policy.setReceiveTimeout(600000);

        return accountWebServiceProxy;
    }

    /**
    * 测试搜索用户
    */
    @Test
    public void getUser() {
        AccountSoapService accountWebService = creatClient();
        GetUserResult result = accountWebService.getUser(1);
    }
}
