package com.abc.hotelsys.listener;

import com.abc.hotelsys.config.VersionInfo;
import com.abc.hotelsys.controller.HotelMgrServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener
public class AppListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("上下文监听器启动了，在这里可以做一些应用程序启动的时候的整体系统的初始化操作。");
        sce.getServletContext().setAttribute("footerHtml",VersionInfo.buildFooterStr());
        logger.debug("生成并保存版权信息网页脚本到应用程序范围:"+VersionInfo.buildFooterStr());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("上下文监听器要停止了，在这里可以做一些整个app整体的内存清理操作。");
        System.out.println(sce.getServletContext().getAttribute("footerHtml"));
    }

}
