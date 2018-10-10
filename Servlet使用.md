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

控制Servlet创建的时间
--
  load-on-startup属性实现
  ```
        load-on-startup参数：
        配置在servlet节点中，可以指定servlet被创建的时机。
        若为负数，则在第一次请求时才被创建和初始化同时service；
        若为0或证书，则在当前web应用被Servlet容器加载时创建实例(创建和初始化立即就被执行)，且数字越小越早被创建
 ```
如果不写或者值是负数的话，那在 Servlet 容器启动时并不会初始化这个 Servlet，而会在第一次用到时才进行初始化。


ServletConfig
--

init方法的传入参数为ServletConfig对象，那么ServletConfig有什么用呢？

ServletConfig的定义
```
public interface ServletConfig {
    String getServletName();

    ServletContext getServletContext();

    String getInitParameter(String var1);

    Enumeration<String> getInitParameterNames();
}

```

来看看Servlet接口的定义：
```
  public void init(ServletConfig servletConfig) throws ServletException {
     
    }
```

ServletConfig：封装了Servlet的配置信息，并且可以获取ServletContext对象

>String getInitParameter(String var1)为获取指定参数名var1对应的value
>

在xml中可以进行初始化参数如下：
```
        <init-param>
            <param-name>user</param-name>
            <param-value>root</param-value>
        </init-param>
        <init-param>
            <param-name>password</param-name>
            <param-value>12306</param-value>
        </init-param>
```

添加下列代码：
```
 public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
        Enumeration<String>names = servletConfig.getInitParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            System.out.println("name"+" "+name);
            System.out.println("value: "+" "+servletConfig.getInitParameter(name));
        }
    }
```

发起一次请求，输出为:
```
HelloServlet's constructor...
init
name password
value:  12306
name user
value:  root
service
```

ServletContext
--
>Servlet引擎为每个WEB应用程序都创建一个对应的ServletContext对象，ServletContext对象被包含在ServletConfig对象中，调用ServletConfig.getServletConfig方法可以返回ServletContext对象的引用。

由于一个WEB应用程序中的所有Servlet都共享一个ServletContext对象，所以，ServletContext对象呗称为application对象(该对象代表当前Web应用程序对象，从中可以获取到当前web对象的各个方面的信息)

功能：


```
<!--配置当前web应用的初始化参数-->
    <context-param>
        <param-name>driver</param-name>
        <param-value>com.oracle.nio.oracle</param-value>
    </context-param>
    <context-param>
        <param-name>jdbcUrl</param-name>
        <param-value>jdbc:mysql:///atguigu</param-value>
    </context-param>
```

在Servlet实现类的init函数中添加如下代码：
```
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
```

结果如下：
```
HelloServlet's constructor...
init
name driver
value:  com.oracle.nio.oracle
name jdbcUrl
value:  jdbc:mysql:///atguigu
service
```

**web应用的初始化参数与servlet的初始化参数的区别**
web应用的初始化参数作用于整个web程序，是全局的；而servlet的初始化参数作用于特定的一个servlet，是局部的

