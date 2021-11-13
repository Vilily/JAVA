/**
 * @author Bao Wenjie
 */
package com.baowj.commonclass;

import org.junit.Test;

public class JavaStringBuffer {

    @Test
    public void test1() {
        StringBuffer a = new StringBuffer("hgffe");
        a.setCharAt(0, 'm');
        System.out.println(a);
    }
}