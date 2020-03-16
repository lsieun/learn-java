package com.lsieun.regex;

import org.junit.Test;

public class B_RegexConstructs {
    @Test
    public void testPredefinedCharacterClasses(){
        System.out.println("a".matches("."));
        System.out.println("1".matches("\\d"));
        System.out.println("%".matches("\\D"));
        System.out.println("\r".matches("\\s"));
        System.out.println("^".matches("\\S"));
        System.out.println("a".matches("\\w"));
    }

    @Test
    public void testGreedyQuantifiers() {
        System.out.println( "a".matches(".") );
        System.out.println( "a".matches("a") );
        System.out.println("a".matches("a?") );
        System.out.println( "aaa".matches("a*") );
        System.out.println( "".matches("a+") );
        System.out.println( "aaaaa".matches("a{5}") );
        System.out.println( "aaaaaaaaa".matches("a{5,8}") );
        System.out.println( "aaa".matches("a{5,}") );
        System.out.println( "aaaaab".matches("a{5,}") );
    }

    @Test
    public void testCharacterClasses() {
        System.out.println( "a".matches("[a]") );
        System.out.println( "aa".matches("[a]+") );
        System.out.println( "abc".matches("[abc]{3,}") );
        System.out.println( "abc".matches("[abc]+") );
        System.out.println( "dshfshfu1".matches("[^abc]+") );
        System.out.println( "abcdsaA".matches("[a-z]{5,}") );
        System.out.println( "abcdsaA12".matches("[a-zA-Z]{5,}") );
        System.out.println( "abcdsaA12".matches("[a-zA-Z0-9]{5,}") );
        System.out.println( "abdxyz".matches("[a-c[x-z]]+"));
        System.out.println( "bcbcbc".matches("[a-z&&[b-c]]{5,}"));
        System.out.println( "tretrt".matches("[a-z&&[^b-c]]{5,}"));

    }
}
