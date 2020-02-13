修改端口号
使用properties文件方式：
在src/main/resources目录下创建：application.properties，添加如下配置即可修改端口号：

server.port=8088

使用yml文件方式：
在src/main/resources目录下创建：application.yml，添加如下配置即可修改端口号：

server:
  port:8088
  
修改项目访问路径
使用properties文件方式：
在application.properties，添加如下配置即可修改项目访问路径：

server.context-path=/springboot-demo
使用yml文件方式：
在application.yml，追加如下配置即可修改项目访问路径：

server:
  port:8088
  context-path:/springboot-demo

server.port=8088
server.context-path=/springboot-demo
