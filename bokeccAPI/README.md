# bokecc API #

## 1、账号 ##

	网址：www.bokecc.com
<!--
	账号：13210088250
	密码：123456789a
-->
	UserID: 0438CD3A0AB20794
	API KEY: E5C7zpFmqq3VAE0wGrcGOow7nJdhZHLc 


## 2、Live API 开发指南 ##

[Live API 开发指南](http://doc.bokecc.com/live/dev/liveapi/)

### 2.1、Live API的作用 ###

	与CC直播进行对接，使用直播系统的主要功能。

### 2.2、使用前提 ###

	（必须）通过CC直播后台获取API Key；否则，无法调用成功。

### 2.3、通信约定 ###

- **GET请求**：通信接口基于 HTTP 协议，如没特别说明，均采用 GET 请求。
- **编码格式**：只接受 UTF-8 格式编码的信息，返回的数据也都是 UTF-8 编码的。
- **QueryString加密**：当需要通过 GET 请求传递参数时，QueryString 里面的 value 值都需要进行 URL Encode 之后，再进行THQS加密。

### 2.4、加密 ###
所有的 HTTP 通信都是加密的，加密的核心思想是将原始的 **QueryString** 转换为和**请求时间**相关的 **HashedQueryString**，我们称这个加密算法为 **THQS 算法**。

[附录2: HTTP通信加密算法](http://doc.bokecc.com/live/dev/liveapi/#toc_37)


## 3、Maven项目搭建 ##

### 3.1、pom.xml ###

pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.bokecc.api</groupId>
    <artifactId>LiveAPI</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.4</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.0</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.0</version>
        </dependency>
        <!-- httpclient -->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.4</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
    </dependencies>

</project>
```

### 3.2、MD5Util ###

MD5Util.java

```java
package com.bokecc.api.util;

import java.security.MessageDigest;

public class MD5Util {
    public static void main(String[] args) {
        String pwd = getMD5("password");
        System.out.println(pwd);
    }

    //生成MD5
    public static String getMD5(String message) {
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象
            byte[] messageByte = message.getBytes("UTF-8");
            byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位
            md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    // 二进制转十六进制
    public static String bytesToHex(byte[] bytes) {
        StringBuffer hexStr = new StringBuffer();
        int num;
        for (int i = 0; i < bytes.length; i++) {
            num = bytes[i];
            if(num < 0) {
                num += 256;
            }
            if(num < 16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }
}

```

### 3.3、CharacterUtil ###

CharacterUtil.java

```java
package com.bokecc.api.util;

public class CharacterUtil {
    public static void main(String[] args) {
        String str = gbEncoding("测试");
        System.out.println(str);
        str = decodeUnicode(str);
        System.out.println(str);
    }
    //中文转Unicode
    public static String gbEncoding(final String gbString) {   //gbString = "测试"
        char[] utfBytes = gbString.toCharArray();   //utfBytes = [测, 试]
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }

    //Unicode转中文
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    public static String parseUnicode(String line){
        int len=line.length();
        char[] out=new char[len];//保存解析以后的结果
        int outLen=0;
        for(int i=0;i<len;i++){
            char aChar=line.charAt(i);
            if(aChar=='\\'){
                aChar=line.charAt(++i);
                if(aChar=='u'){
                    int value=0;
                    for(int j=0;j<4;j++){
                        aChar=line.charAt(++i);
                        switch (aChar) {
                            case '0': case '1': case '2': case '3': case '4':
                            case '5': case '6': case '7': case '8': case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a': case 'b': case 'c':
                            case 'd': case 'e': case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A': case 'B': case 'C':
                            case 'D': case 'E': case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default: throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++]=(char)value;
                }else{
                    if (aChar == 't') aChar = '\t';
                    else if (aChar == 'r') aChar = '\r';
                    else if (aChar == 'n') aChar = '\n';
                    else if (aChar == 'f') aChar = '\f';
                    out[outLen++] = aChar;
                }
            }else{
                out[outLen++] = aChar;
            }
        }
        return new String (out, 0, outLen);
    }
}

```




### 3.4、CCUtil ###

CCUtil.java

```java
package com.bokecc.api.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class CCUtil {
    private static String domain_name = "http://api.csslcloud.net/api";
    private static String API_KEY = "E5C7zpFmqq3VAE0wGrcGOow7nJdhZHLc";
    public static final String CC_USER_ID = "0438CD3A0AB20794";

    public static String get(String path){
        String result = null;
        try{
            String url = domain_name + path;
            System.out.println("URL: " + url);

            //1、创建HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();

            //2、创建HttpRequest请求
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type", "application/json");

            //3、接收返回结果HttpResponse
            CloseableHttpResponse response = httpClient.execute(httpGet);

            //4、对结果进行处理
            //(4-1)处理StatusLine
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            ProtocolVersion protocolVersion = statusLine.getProtocolVersion();
            String reasonPhrase = statusLine.getReasonPhrase();

            //(4-3)处理HttpEntity
            HttpEntity entity = response.getEntity();
            StringBuffer sb = new StringBuffer();
            if(entity != null){
                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    //sb.append(inputLine + "\r\n");
                    sb.append(inputLine);
                }
                reader.close();
                result = sb.toString();
            }
            else{
                System.out.println("获取不到内容");
            }

            //5、关闭资源连接
            response.close();
            httpClient.close();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return result;
    }

    public static String createTimeHashedQueryString(Map<String, String> queryMap){
        queryMap.put("userid",CC_USER_ID);
        return createTimeHashedQueryString(queryMap,System.currentTimeMillis(),API_KEY);
    }

    public static String createTimeHashedQueryString(Map<String, String> queryMap, long time){
        return createTimeHashedQueryString(queryMap,time,API_KEY);
    }

    /**
     * 功能：将一个Map按照Key字母升序构成一个QueryString. 并且加入时间混淆的hash串
     * @param queryMap  query内容
     * @param time  加密时候，为当前时间；解密时，为从querystring得到的时间；
     * @param salt   加密salt
     * @return
     */
    public static String createTimeHashedQueryString(Map<String, String> queryMap, long time, String salt){

        Map<String, String> map = new TreeMap<String, String>(queryMap);
        String qs = getQueryString(map);
        if (qs == null) {
            return null;
        }

        time = time / 1000;
        String queryString = String.format("%s&time=%d&salt=%s", qs, time, salt);
        String hash = MD5Util.getMD5(queryString);
        hash = hash.toUpperCase();
        String thqs = String.format("%s&time=%d&hash=%s", qs, time, hash);

        return thqs;
    }

    private static String getQueryString(Map parametersMap){
        return getQueryStringFromMap(parametersMap,true);
    }

    private static String getQueryStringFromMap(Map parametersMap, boolean isUrlEncoding/*,String charset*/) {
        StringBuilder sb = new StringBuilder();
        for(Object obj:parametersMap.keySet()){
            String value=(String) parametersMap.get(obj);
            if(isUrlEncoding){
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                    if(StringUtils.isNotBlank(value)){
                        parametersMap.put(obj, value);
                    }
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
            sb.append(obj).append("=").append(value).append("&");
        }
        return sb.toString().replaceAll("&$", "");

    }
}


```

## 4、Live API调用 ##

### 4.1、A_RoomTest_Basic ###

A_RoomTest_Basic.java

```java
package com.bokecc.api.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import com.bokecc.api.util.CCUtil;

public class A_RoomTest_Basic {

    /**
     * 创建直播间
     */
    @Test
    public void testRoomCreate() {
        //（1）path
        String path = "/room/create";

        //（2）queryString
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("name", "被时光掩埋的秘密..." + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        queryMap.put("desc", "有些事，有个人，一辈子都会隐匿于心底某个角落，从不曾离开，亦无可取代。");
        queryMap.put("templatetype", "5");
        queryMap.put("authtype", "2");
        queryMap.put("publisherpass", "111222");
        queryMap.put("assistantpass", "123321");
        queryMap.put("playpass", "3333333");
        queryMap.put("checkurl", "");
        queryMap.put("barrage", "1");//是否开启弹幕。0：不开启；1：开启
        queryMap.put("foreignpublish", "0");//是否开启第三方推流。0：不开启；1：开启
        queryMap.put("openlowdelaymode", "0");//开启直播低延时模式。0为关闭；1为开启
        queryMap.put("showusercount", "1");//在页面显示当前在线人数。0表示不显示；1表示显示
        queryMap.put("openhostmode", "0");//开启主持人模式，"0"表示不开启；"1"表示开启
        queryMap.put("warmvideoid", "");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 编辑直播间
     */
    @Test
    public void testRoomUpdate() {
        //（1）path
        String path = "/room/update";

        //（2）queryString
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid", "684C73C6F26C92D09C33DC5901307461");
        queryMap.put("name", "被时光" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        queryMap.put("desc", "从不曾离开，亦无可取代。");
        queryMap.put("authtype", "2");
        queryMap.put("publisherpass", "111222");
        queryMap.put("assistantpass", "123321");
        queryMap.put("playpass", "");
        queryMap.put("checkurl", "");
        queryMap.put("barrage", "0");//是否开启弹幕。0：不开启；1：开启
        queryMap.put("openlowdelaymode", "0");//开启直播低延时模式。0为关闭；1为开启
        queryMap.put("showusercount", "1");//在页面显示当前在线人数。0表示不显示；1表示显示
        queryMap.put("warmvideoid", "");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取直播间信息
     */
    @Test
    public void testRoomById() {
        //（1）path
        String path = "/room/search";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","684C73C6F26C92D09C33DC5901307461");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 关闭直播间
     */
    @Test
    public void testRoomClose() {
        //（1）path
        String path = "/room/close";

        //（2）queryString
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid", "684C73C6F26C92D09C33DC5901307461");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;
        System.out.println("fullPath = " + fullPath);

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取直播间列表
     */
    @Test
    public void testRoomList() {
        //（1）path
        String path = "/room/info";

        //（2）queryString
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("pagenum", "10");
        queryMap.put("pageindex", "1");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

}

```


### 4.2、A_RoomTest_Extra ###

A_RoomTest_Extra.java

```java
package com.bokecc.api.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bokecc.api.util.CCUtil;

public class A_RoomTest_Extra {

    /**
     * 获取直播间模板信息
     */
    @Test
    public void testRoomViewTemplateInfo(){
        //（1）path
        String path = "/viewtemplate/info";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取直播间代码
     */
    @Test
    public void testRoomCode(){
        //（1）path
        String path = "/room/code";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","7463057A19482FC69C33DC5901307461");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }
}

```

### 4.3、A_RoomTest_Live ###

A_RoomTest_Live.java

```java
package com.bokecc.api.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bokecc.api.util.CCUtil;

public class A_RoomTest_Live {
    /**
     * 获取直播间直播状态
     */
    @Test
    public void testRoomsPublishing(){
        //（1）path
        String path = "/rooms/publishing";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomids","5BE97CA6A277ADAE9C33DC5901307461,8DED3B36E5595DD39C33DC5901307461");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取直播列表:获取指定直播间下历史直播信息
     */
    @Test
    public void testLiveInfo(){
        //（1）path
        String path = "/v2/live/info";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","D2DE3306F03F63949C33DC5901307461");
        queryMap.put("pagenum","10");
        queryMap.put("pageindex","1");
//        queryMap.put("starttime","");
//        queryMap.put("endtime","");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;
        System.out.println("fullPath = " + fullPath);

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取正在直播的直播间列表
     */
    @Test
    public void testRoomsBroadcasting(){
        //（1）path
        String path = "/rooms/broadcasting";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }
}

```

### 4.4、A_RoomTest_Statis ###

A_RoomTest_Statis.java

```java
package com.bokecc.api.test;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bokecc.api.util.CCUtil;

public class A_RoomTest_Statis {

    /**
     * 获取直播间连接数:获取直播间的连接数统计信息
     */
    @Test
    public void testRoomStatisConnections(){
        //（1）path
        String path = "/statis/connections";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","D2DE3306F03F63949C33DC5901307461");
        queryMap.put("starttime","2015-01-02 12:30:00");
        queryMap.put("endtime","2019-01-02 13:30:00");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;
        System.out.println("fullPath = " + fullPath);

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取直播间内用户进出信息
     */
    @Test
    public void testRoomStatisUserAction(){
        //（1）path
        String path = "/statis/useraction";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","568536A15595D1B99C33DC5901307461");
        queryMap.put("starttime","2018-02-26 10:11");
        queryMap.put("endtime","2018-02-26 18:59");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取观看直播的统计信息
     */
    @Test
    public void testRoomStatisUserView(){
        //（1）path
        String path = "/statis/userview";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("liveid","EC53C5CB27D8C096");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }
}

```

### 4.5、B_RecordTest ###

B_RecordTest.java

```java
package com.bokecc.api.test;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bokecc.api.util.CCUtil;

public class B_RecordTest {

    /**
     * 查询回放列表:通过该接口可以分页获取回放列表的信息
     */
    @Test
    public void testRecordList(){
        //（1）path
        String path = "/v2/record/info";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","6CBC06FD41D5C6849C33DC5901307461");
        queryMap.put("pagenum","10");
        queryMap.put("pageindex","1");
//        queryMap.put("starttime","");
//        queryMap.put("endtime","");
        queryMap.put("liveid","EC53C5CB27D8C096");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 查询回放信息:获取单个回放信息
     */
    @Test
    public void testRecordById(){
        //（1）path
        String path = "/v2/record/search";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("recordid","12C410AB0164EFD7");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

}

```

### 4.6、C_OtherTest ###

C_OtherTest.java

```java
package com.bokecc.api.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.bokecc.api.util.CCUtil;

public class C_OtherTest {

    /**
     * 获取聊天信息
     */
    @Test
    public void testLiveChatMsg(){
        //（1）path
        String path = "/live/chatmsg";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","FD12DE0516BE165C9C33DC5901307461");
        queryMap.put("liveid","26909CC07F66582C");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取抽奖信息
     */
    @Test
    public void testLiveLotterys(){
        //（1）path
        String path = "/live/lotterys";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","6CBC06FD41D5C6849C33DC5901307461");
        queryMap.put("liveid","EC53C5CB27D8C096");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取问答信息
     */
    @Test
    public void testLiveQAS(){
        //（1）path
        String path = "/live/qas";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","6CBC06FD41D5C6849C33DC5901307461");
        queryMap.put("liveid","EC53C5CB27D8C096");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;
        System.out.println("fullPath = " + fullPath);

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取签到信息
     */
    @Test
    public void testLiveRollCall(){
        //（1）path
        String path = "/live/rollcall";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","6CBC06FD41D5C6849C33DC5901307461");
        queryMap.put("liveid","EC53C5CB27D8C096");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取签到用户信息
     */
    @Test
    public void testLiveRollCallViewer(){
        //（1）path
        String path = "/live/rollcall/viewers";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("roomid","6CBC06FD41D5C6849C33DC5901307461");
        queryMap.put("liveid","EC53C5CB27D8C096");
        queryMap.put("rollcallid","");

        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;
        System.out.println("fullPath = " + fullPath);

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取问卷信息
     */
    @Test
    public void testLiveQuestionnaires(){
        //（1）path
        String path = "/live/questionnaires";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("liveid","EC53C5CB27D8C096");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;
        System.out.println("fullPath = " + fullPath);

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

    /**
     * 获取用户答卷信息
     */
    @Test
    public void testLiveQuestionnairesViewers(){
        //（1）path
        String path = "/live/questionnaire/viewers";

        //（2）queryString
        Map<String,String> queryMap = new HashMap<String, String>();
        queryMap.put("liveid","EC53C5CB27D8C096");
        queryMap.put("questionnaireid","");
        String thqs = CCUtil.createTimeHashedQueryString(queryMap);

        //（3）path + queryString
        String fullPath = path + "?" + thqs;
        System.out.println("fullPath = " + fullPath);

        //（4）result
        String result = CCUtil.get(fullPath);
        System.out.println(result);
    }

}

```





