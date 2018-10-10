Servlet的jar包是在Tomcat的lib目录下的，所以在一个web工程中想要找到javax.servlet.*的话，必须先从Tomcat下导入servlet-api.jar。

这也是说明了为什么Tomcat是Servlet容器的原因！

实现一个Servlet具体类
--
```
import javax.servlet.*;
import java.io.IOException;

public class HelloServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
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

    }
    public HelloServlet(){
      System.out.println("HelloServlet's constructor...");

    }
}
```

web.xml配置和映射该Servlet
--
```
 <!--配置和映射Servlet-->
    <servlet>
        <servlet-name>helloServlet</servlet-name>
        <servlet-class>com.lihuijuan.javaweb.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>helloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
```

启动Tomcat
--

* 启动Tomcat服务
* 在浏览器中输入http://localhost:8080/firstweb/hello

第一次发起请求时，输出为
```
HelloServlet's constructor...
init
service
```
之后每次请求时，都增加一行service,也就是调用service方法
```
service
```
当关闭Tomcat服务器时，调用Servlet的destroy方法
