# Http Request

URL：

- http://blog.51cto.com/lsieun/1775254
- http://www.ntu.edu.sg/home/ehchua/programming/webprogramming/http_basics.html


## 1. HTTP Request的知识

### 1.1 HTTP Request的结构

HTTP请求由四部分组成：请求行、请求头、一个空行和实体内容（可选）。

HTTP请求的四部分：

- 请求行
- 请求头
- （一个空行）
- 实体内容（只有POST请求时才有）

HTTP请求的一个示例：

```txt
GET /myweb/hello HTTP/1.1               --请求行
Host: localhost:8080                    --请求头（多个key-value对象）
User-Agent: Mozilla/5.0
Accept: text/html,application/xhtml+xml
Accept-Language: zh-cn,en-us;q=0.8,zh;q=0.5,en;q=0.3
Accept-Encoding: gzip, deflate
Connection: keep-alive
                                        -- 一个空行
name=rk&password=123456                 --（可选）实体内容
```

### 1.2 请求行

```txt
GET /myweb/hello HTTP/1.1               --请求行
```

HTTP协议版本

- `HTTP/1.0`：当前浏览器客户端与服务器端建立连接之后，只能发送一次请求，一次请求之后连接关闭。
- `HTTP/1.1`：当前浏览器客户端与服务器端建立连接之后，可以在一次连接中发送多次请求。（现在基本上都使用1.1）

请求资源

- `URL`:  统一资源定位符，例如`http://localhost:8080/myweb/hello.html`。URL只能定位互联网资源，是URI的子集。
- `URI` 统一资源标记符，例如`/myweb/hello`。URI用于标记任何资源。资源，可以是本地文件系统的资源，也可以是局域网的资源（`//192.168.14.10/myweb/index.html`），还可以是互联网资源。

请求方式

- 常见的请求方式： `GET`、 `POST`、 `HEAD`、 `TRACE`、 `PUT`、 `CONNECT`、`DELETE`
- 常用的请求方式： `GET`  和 `POST`

表单提交示例：

```html
<form action="提交地址" method="GET/POST">
    Username: <input type="text" name="username"/>
    Password: <input type="text" name="password"/>
    <input type="submit" value="OK"/>
</form>
```

**GET   vs  POST 区别**：

| GET方式                                                      | POST方式                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| a）地址栏（URI）会跟上参数数据。以?开头，多个参数之间以&分割。<br/>b）GET提交参数数据有限制，不超过1KB。<br/>c）GET方式不适合提交敏感密码。<br/>d）注意： 浏览器直接访问的请求，默认提交方式是GET方式 | a）参数不会跟着URI后面。参数而是跟在请求的实体内容中。<br/>b）POST提交的参数数据没有限制。<br/>c）POST方式提交敏感数据。 |

### 1.3 请求头

```txt
Accept: text/html,image/*       -- 浏览器接受的数据类型
Accept-Charset: ISO-8859-1      -- 浏览器接受的编码格式
Accept-Encoding: gzip,compress  --浏览器接受的数据压缩格式
Accept-Language: en-us,zh       --浏览器接受的语言
Host: www.abcd.org:80           --（必须的）当前请求访问的目标地址（主机:端口）
If-Modified-Since: Tue, 11 Jul 2000 18:23:51 GMT    --浏览器最后的缓存时间
Referer: http://www.abcd.org/index.jsp              -- 当前请求来自于哪里
User-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0)    --浏览器类型
Cookie:name=rk                  -- 浏览器保存的cookie信息
Connection: close/Keep-Alive    -- 浏览器跟服务器连接状态。
                                -- close: 连接关闭  keep-alive：保存连接。
Date: Tue, 11 Jul 2000 18:23:51 GMT     -- 请求发出的时间
```

### 1.4 实体内容

只有POST提交的参数会放到实体内容中。

## 2. HttpServletRequest对象

`HttpServletRequest`对象作用是用于获取请求数据。

package: `javax.servlet.http.HttpServletRequest`
class: `public interface HttpServletRequest extends ServletRequest`

### 2.1 HttpServletRequest对象核心API


| 类别     | API                         | 说明                 |
| -------- | --------------------------- | -------------------- |
| 请求行   | `request.getMethod()`       | 请求方式             |
|          | `request.getRequetURI()`    | 请求资源             |
|          | `request.getRequetURL()`    | 请求URL              |
|          | `request.getProtocol()`     | 请求HTTP协议版本     |
| 请求头   | `request.getHeader("名称")` | 根据请求头获取请求值 |
|          | `request.getHeaderNames()`  | 获取所有的请求头名称 |
| 实体内容 | `request.getInputStream()`  | 获取实体内容数据     |


请求行

- `String getMethod()`: Returns the name of the HTTP method with which this request was made, for example, GET, POST, or PUT. 
- `String getRequestURI()`: Returns the part of this request's URL from the protocol name up to the query string in the first line of the HTTP request.
- `StringBuffer getRequestURL()`: Reconstructs the URL the client used to make the request.
- `String getProtocol()`: Returns the name and version of the protocol the request uses in the form `protocol/majorVersion.minorVersion`, for example, `HTTP/1.1`. 这是在父接口`SevletRequest`中定义的方法。

请求头

- `String getHeader(String name)`: Returns the value of the specified request header as a String.
- `Enumeration<String> getHeaderNames()`: Returns an enumeration of all the header names this request contains.

实体内容

- `ServletInputStream getInputStream()`: Retrieves the body of the request as binary data using a `ServletInputStream`. 这是在父接口`SevletRequest`中定义的方法。


### 2.2 传递的请求参数如何获取

在浏览器发送HTTP协议的时候，`GET`方式是将参数放在URI的后面，`POST`方式是将参数放在实体内容中。

获取请求参数的API

- 获取GET方式参数：`request.getQueryString()`;
- 获取POST方式参数：`request.getInputStream()`;

问题：但是以上两种不通用，而且获取到的参数还需要进一步地解析。所以可以使用统一方便的获取参数的方式：


- `request.getParameter("参数名");`  根据参数名获取参数值（注意，只能获取**一个值**的参数）
- `request.getParameterValue("参数名“)；`根据参数名获取参数值（可以获取**多个值**的参数）
- `request.getParameterNames();`   获取所有参数名称列表


### 2.3 请求参数编码问题

修改POST方式参数编码（这种方式只对POST请求的实体内容有效）：

```java
request.setCharacterEncoding("utf-8");
String name = request.getParameter("name");
```

修改GET方式参数编码：

```java
String name = new String(name.getBytes("iso-8859-1"),"utf-8"); // 手动解码
```

> 注意：在Tomcat 8之后的GET请求方式，就不需要进行手动解码了。Tomcat 7还是需要进行手动解码的。

In Tomcat 8 starting with 8.0.0 (8.0.0-RC3, to be specific), the default value of `URIEncoding` attribute on the `<Connector>` element depends on "strict servlet compliance" setting. The default value (strict compliance is off) of `URIEncoding` is now `UTF-8`. If "strict servlet compliance" is enabled, the default value is `ISO-8859-1`.

参考： https://wiki.apache.org/tomcat/FAQ/CharacterEncoding#Q8

进一步说明：

- 在`$CATALINA_HOME/conf`目录下有`server.xml`文件，里面有`<Connector>`标签，该标签可以配置`URIEncoding`属性。
- 在Tomcat 8中，`URIEncoding`的默认值是`UTF-8`，因而**不需要**手动解码。如果强行进行转码，中文就会乱码。[Link](http://tomcat.apache.org/tomcat-8.0-doc/config/http.html)
- 在Tomcat 7中，`URIEncoding`的默认值是`ISO-8859-1`，因此**需要**手动解码。如果不进行转码，那么中文就会出现乱码的情况。[Link](http://tomcat.apache.org/tomcat-7.0-doc/config/http.html)