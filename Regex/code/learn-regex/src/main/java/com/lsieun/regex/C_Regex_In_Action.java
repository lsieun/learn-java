package com.lsieun.regex;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C_Regex_In_Action {

    /**
     * 需求：匹配是否为一个合法的手机号码。
     * 第1位：只能是1开头
     * 第2位：3 4 5 7 8
     * 长度：11位
     */
    public void matchesPhone(String phone){
        String reg = "1[34578]\\d{9}";
        System.out.println(phone.matches(reg)?"合法手机号":"非法手机号");
    }
    @Test
    public void testmatchesPhone() {
        String phone = "13245678985";
        matchesPhone(phone);
    }

    /**
     * 需求2：匹配固定电话
     * 区号-主机号
     * 区号首位是0， 长度3~4位
     * 主机号首位不能是0，长度7~8位
     */
    public void matchesTel(String tel){
        String reg = "0\\d{2,3}-[1-9]\\d{6,7}";
        System.out.println(tel.matches(reg)?"合法固话":"非法固话");
    }

    @Test
    public void testMatchesTel() {
        String tel = "010-7788946";
        matchesTel(tel);
    }

    /**
     * 按照空格进行切割
     */
    public void splitSpace() {
        String str = "我    的  名  字   叫   永 不 放   弃";
        String reg = "[ ]+";
        String[] array = str.split(reg);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void testSplitSpace(){
        splitSpace();
    }

    /**
     * 根据重叠词进行分隔
     */
    public void splitDuplicateWord(){
        String str = "祝大大家明明明天玩玩玩玩的开开开开开心";
        String reg = "(.)\\1+";
        String[] array = str.split(reg);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void testSplitDuplicateWord() {
        splitDuplicateWord();
    }

    /**
     * 将手机号替换为****
     */
    public void replaceAllPhone(){
        StringBuilder sb = new StringBuilder();
        sb.append("北京移动15810103829北京联通13520983362");
        sb.append("北京移动13661287209北京联通13683268910");
        sb.append("北京移动15101682015北京联通13520238157");
        sb.append("北京移动13521658027北京联通15010729506");
        sb.append("北京移动15201172732北京联通15810060872");
        sb.append("北京移动13552915937北京联通13260070970");
        String str = sb.toString();
        String reg = "1[34578]\\d{9}";
        String newStr = str.replaceAll(reg, "****");
        System.out.println(newStr);
    }

    @Test
    public void testReplaceAllPhone(){
        replaceAllPhone();
    }

    /**
     * 去除多余的重复字，只留下一个字
     */
    public void replaceAllDuplicateWord(){
        String str = "天天道道道酬酬酬酬勤勤勤勤勤勤";
        String newString = str.replaceAll("(.)\\1+","$1");
        System.out.println(newString);
    }

    @Test
    public void testReplaceAllDuplicateWord() {
        replaceAllDuplicateWord();
    }

    /**
     * 找出三个字母组成的单词
     */
    public void patternFind(){
        String content = "You are one of the most beautiful girl I have ever seen.";
        String reg = "\\b[a-zA-Z]{3}\\b";

        //先把regular expression的字符串编译成Pattern对象
        Pattern p = Pattern.compile(reg);
        //使用Pattern对象匹配内容字符串(content)，从而产生一个Matcher对象
        Matcher matcher = p.matcher(content);

        while(matcher.find()){
            String value = matcher.group();
            System.out.println(value);
        }
    }

    @Test
    public void testPatternFind(){
        patternFind();
    }

    @Test
    public void testPatter_Boundary(){
        System.out.println("Hello World".matches("Hello\\bWorld"));//false
        System.out.println("Hello World".matches("Hello\\b World"));//true
    }

    @Test
    public void testEmail(){
        String email = "-abc@qq.com.cn.abc";
        String reg = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        System.out.println(email.matches(reg));
    }
}
