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
