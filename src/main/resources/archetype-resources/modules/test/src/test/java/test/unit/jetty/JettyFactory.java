/*
 * Copyright (c) 2014, lingang.chen@gmail.com  All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package ${package}.test.unit.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Reason:	 创建Jetty Server的工厂类.
 * 
 * @author chenlg
 */
public class JettyFactory {

    public static final String DEFAULT_WEBAPP_PATH = "../../server/src/main/webapp";

    public static final String CONTEXT_PATH        = "/${projectId}";

    /**
     * 创建用于开发运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
     */
    public static Server createServerInSource(int port) {
        Server server = new Server();
        // 设置在JVM退出时关闭Jetty的钩子。
        server.setStopAtShutdown(true);

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        // 解决Windows下重复启动Jetty居然不报告端口冲突的问题.
        connector.setReuseAddress(false);
        server.setConnectors(new Connector[] { connector });

        WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, CONTEXT_PATH);

        server.setHandler(webContext);

        return server;
    }

    /**
     * 快速重新启动application，重载所有target/classes与target/test-classes.
     */
    public static void reloadContext(Server server) throws Exception {
        WebAppContext context = (WebAppContext) server.getHandler();

        System.out.println("[INFO] Application reloading");
        context.stop();

        WebAppClassLoader classLoader = new WebAppClassLoader(context);
        //        
        //运行需要的模块
        classLoader.addClassPath(BIZ_PROCESS_DAEMON);
        classLoader.addClassPath(BIZ_PROCESS_SERCURITY);
        classLoader.addClassPath(BIZ_SERVICE_ESCAPE);
        classLoader.addClassPath(BIZ_SERVICE_LOGIC);
        classLoader.addClassPath(BIZ_SHARED);
        classLoader.addClassPath(COMMON_SERVICE_FACADE);
        classLoader.addClassPath(COMMON_SERVICE_REMOTE);
        classLoader.addClassPath(COMMON_SERVICE_RESPONSE);
        classLoader.addClassPath(COMMON_SHARED);
        classLoader.addClassPath(COMMON_UTILS);
        classLoader.addClassPath(CORE_DOMAIN);
        classLoader.addClassPath(CORE_PERSISTANT_PO);
        classLoader.addClassPath(CORE_PERSISTANT_REPOSITORY);
        classLoader.addClassPath(CORE_PROCESS_CACHE);
        classLoader.addClassPath(CORE_PROCESS_EXTENSION);
        classLoader.addClassPath(CORE_SHARED);

        //当前
        classLoader.addClassPath("target/classes");
        classLoader.addClassPath("target/test-classes");
        context.setClassLoader(classLoader);

        context.start();

        System.out.println("[INFO] Application reloaded");
    }

    public static final String BIZ_PROCESS_DAEMON         = "../../modules/biz/process/daemon/target/classes";
    public static final String BIZ_PROCESS_SERCURITY      = "../../modules/biz/process/sercurity/target/classes";
    public static final String BIZ_SERVICE_ESCAPE         = "../../modules/biz/service/escape/target/classes";
    public static final String BIZ_SERVICE_LOGIC          = "../../modules/biz/service/logic/target/classes";
    public static final String BIZ_SHARED                 = "../../modules/biz/shared/target/classes";
    public static final String COMMON_SERVICE_FACADE      = "../../modules/common/service/facade/target/classes";
    public static final String COMMON_SERVICE_REMOTE      = "../../modules/common/service/remote/target/classes";
    public static final String COMMON_SERVICE_RESPONSE    = "../../modules/common/service/response/target/classes";
    public static final String COMMON_SHARED              = "../../modules/common/shared/target/classes";
    public static final String COMMON_UTILS               = "../../modules/common/utils/target/classes";
    public static final String CORE_DOMAIN                = "../../modules/core/domain/target/classes";
    public static final String CORE_PERSISTANT_PO         = "../../modules/core/persistant/po/target/classes";
    public static final String CORE_PERSISTANT_REPOSITORY = "../../modules/core/persistant/repository/target/classes";
    public static final String CORE_PROCESS_CACHE         = "../../modules/core/process/cache/target/classes";
    public static final String CORE_PROCESS_EXTENSION     = "../../modules/core/process/extension/target/classes";
    public static final String CORE_SHARED                = "../../modules/core/shared/target/classes";

}
