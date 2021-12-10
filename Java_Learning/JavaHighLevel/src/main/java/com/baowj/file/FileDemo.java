/**
 * @author Bao WJ
 * @date 2021/11/29 10:43
 */
package com.baowj.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileDemo {

    @Test
    public void test1() {
        File file = new File("C:\\Users\\WILL\\Desktop\\test.txt");
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.length());
        System.out.println(file.lastModified());
        System.out.println(file.getParentFile());
    }

    @Test
    public void test2() {
        File file = new File("C:\\Users\\WILL\\Desktop");
        File[] tmp = file.listFiles();
        for (File s : tmp) {
            System.out.println(s);
        }
    }

    @Test
    public void test3() throws IOException {
        File file = new File("test.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("创建成功");
        }
    }

    @Test
    public void test4() throws IOException {
        File file = new File("test.txt");
        if (file.exists()) {
            file.delete();
            System.out.println("删除成功");
        }
    }
}
