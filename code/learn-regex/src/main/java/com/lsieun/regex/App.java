package com.lsieun.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
//        Pattern p = Pattern.compile("(\\d)(.*)(\\d)");
//        String input = "6 example input 4";
//        Matcher m = p.matcher(input);
//        if (m.find()) {
//            // replace first number with "number" and second number with the first
//            String output = m.replaceFirst("number $3$1");  // number 46
//            System.out.println(output);
//        }

        Pattern p = Pattern.compile("(.*)(\\.([jpg|png]))",Pattern.CASE_INSENSITIVE);
        String input = "https://img05.allinmd.cn/public1/M00/0B/0A/wKgBL1nAi9GAUCt5AAdq0Wg2Lnw081.JpG";
        Matcher m = p.matcher(input);
        if (m.find()) {
            // replace first number with "number" and second number with the first
            String output = m.replaceFirst("number $1_c$2");  // number 46
            System.out.println(output);
        }
    }
}
