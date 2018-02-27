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
