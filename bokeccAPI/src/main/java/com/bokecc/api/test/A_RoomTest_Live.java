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
