/**
 * @author Bao Wenjie
 */
package com.baowj.commonclass;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class JavaString {

    @Test
    public void test1() {
        String s1 = "abc";
        String s2 = "abc";
        s1 = "Hello";
        System.out.println(s1 == s2);
        System.out.println(s2);
    }
}