# Tomcat安装Demo

---
Tomcat的版本为7.0.91，官网下载地址为http://tomcat.apache.org/

![1.png-17kB][1]
安装教程见https://blog.csdn.net/yangxingpa/article/details/58174598
![在此输入正文][2]

测试是否安装成功Tomcat
--
在cmd中启动bin目录下的脚本startup
![image.png-9.7kB][3]

那么弹出:
![image.png-70.7kB][4]

在浏览器中输入网址：http://localhost:8080/

出现
![image.png-151.2kB][5]
即为安装成功

建立web工程
--
参考链接：https://www.cnblogs.com/shindo/p/7272646.html
![1.png-55.9kB][6]

* 在web/WEB_INF 目录下创建两个文件夹：classes和lib
classes用来存放编译后输出的class文件，lib用于存放第三方jar包

![image.png-23.9kB][7]

* 修改 index.jsp中的内容
  在body中添加一行
```
我的第一个使用intellij web项目运行成功！
```
 
 添加完成之后index.jsp的内容如下所示:
 ```
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    我的第一个使用intellij web项目运行成功！
  </body>
</html>

 ```

* 配置Tomcat
  将该web工程的输出classed和lib与Tomcat进行关联，关联方法见https://www.cnblogs.com/shindo/p/7272646.html

* 部署web目录
设置输出目录为firstweb（也可以不设置或者设置为任意名字）
![1.png-34.3kB][9]

测试web工程是否搭建成功
--

* 在intellij中启动tomcat服务器

* 在浏览器中输入：http://localhost:8080/firstweb/

结果为：

![image.png-8.3kB][10]


  [1]: http://static.zybuluo.com/lihuijuan114/mae0h9yg6gydrom91abvohmx/1.png
  [2]: http://static.zybuluo.com/lihuijuan114/ejm4rf5tuycwnh4edmd4yoty/image.png
  [3]: http://static.zybuluo.com/lihuijuan114/amlg5wj4yshvsehuv2x4iq96/image.png
  [4]: http://static.zybuluo.com/lihuijuan114/357dcazcystqrjxisbwbhkmo/image.png
  [5]: http://static.zybuluo.com/lihuijuan114/m4qveceqpydae44uf1fvv0jf/image.png
  [6]: http://static.zybuluo.com/lihuijuan114/fcrhw30lpxmx64uxnf71yj0f/1.png
  [7]: http://static.zybuluo.com/lihuijuan114/tyczhrvti3c1p53jcr0yw4z9/image.png
  [8]: http://static.zybuluo.com/lihuijuan114/3bd7fv31bfet5advdfj79zeg/1.png
  [9]: http://static.zybuluo.com/lihuijuan114/zq002zp4rklsqwrx6gw7w7tf/1.png
  [10]: http://static.zybuluo.com/lihuijuan114/udggokd35sdmzn0wwey8ae72/image.png
