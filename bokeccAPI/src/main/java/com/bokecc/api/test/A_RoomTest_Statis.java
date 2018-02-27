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
