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
