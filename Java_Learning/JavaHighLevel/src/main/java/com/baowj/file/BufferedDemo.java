/**
 * @author Bao WJ
 * @date 2021/11/29 17:06
 */
package com.baowj.file;

import org.junit.Test;

import java.io.*;
import java.util.Locale;

public class BufferedDemo {

    @Test
    public void test1() throws FileNotFoundException {
        File file1 = new File("");
        File file2 = new File("");

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
    }


    @Test
    public void test2() throws FileNotFoundException {
        File file1 = new File("");
        File file2 = new File("");

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        InputStreamReader isr = new InputStreamReader(fis);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
    }

    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String str;
        try {
            while (true) {
                str = br.readLine();
                if ("q".equalsIgnoreCase(str)) {
                    System.out.println("程序结束");
                    break;
                } else {
                    System.out.println(str.toUpperCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test3() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("test.txt"));
            ps = new PrintStream(fos, true);
            if (ps != null) {
                System.setOut(ps);
                for (int i = 0; i < 256; i++) {
                    System.out.print((char) i);
                    if (i % 50 == 0) {
                        System.out.println();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    private class Person implements Serializable{
        public static final long serialVersionUID = 422423432L;

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
