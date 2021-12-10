/**
 * @author Bao WJ
 * @date 2021/11/28 20:46
 */
package com.baowj.commonclass;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JavaProperties {
    @Test
    public void test1() throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("jdbc.properties");
        properties.load(fis);

    }
}
