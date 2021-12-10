/**
 * @author Bao WJ
 * @date 2021/11/29 11:26
 */
package com.baowj.file;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderDemo {
    @Test
    public void test1() throws IOException {
        File file = new File("test.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    @Test
    public void test2() throws IOException {
        File file = new File("test.txt");
        FileWriter fileWriter = new FileWriter(file, true);

        String str = "public void test2() throws IOException";
        fileWriter.write(str);

        fileWriter.close();
    }

}
