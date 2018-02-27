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
