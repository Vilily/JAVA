/**
 * @author Bao WJ
 * @date 2021/11/29 16:43
 */
package com.baowj.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamDemo {

    @Test
    public void test1() throws IOException {
        File file = new File("test.txt");

        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] buffer = new byte[100];
        int len;
        while ((len = fileInputStream.read(buffer)) != -1) {
            String str = new String(buffer, 0, len);
            System.out.println(str);
        }
    }
}
