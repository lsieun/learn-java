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
