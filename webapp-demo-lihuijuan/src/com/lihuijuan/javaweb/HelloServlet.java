package com.lihuijuan.javaweb;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
        ServletContext servletContext = servletConfig.getServletContext();
        Enumeration<String>names = servletContext.getInitParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            System.out.println("name"+" "+name);
            System.out.println("value: "+" "+servletContext.getInitParameter(name));
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
    public HelloServlet(){
      System.out.println("HelloServlet's constructor...");

    }
}
