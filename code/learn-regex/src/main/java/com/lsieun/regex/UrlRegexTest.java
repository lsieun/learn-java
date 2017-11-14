package com.lsieun.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlRegexTest {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("(.*)(\\.[jpg|png])(.*)",Pattern.CASE_INSENSITIVE);
        String input = "https://img05.allinmd.cn/public1/M00/0B/0A/wKgBL1nAi9GAUCt5AAdq0Wg2Lnw081.JPG?abc=aaa";
        System.out.println(input);
        Matcher m = p.matcher(input);
        if (m.find()) {
            String output = m.replaceFirst("$1_c$2$3");
            System.out.println(output);
        }
    }
}
