/**
 * @author Bao WJ
 * @date 2021/11/30 19:22
 */
package com.baowj.network;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo {

    public static void main(String[] args) {
        HttpURLConnection urlConnection = null;
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            URL url = new URL("https://www.hust.edu.cn/images/weixintupian_20211126135639.jpg");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            is = urlConnection.getInputStream();
            fos = new FileOutputStream("test.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
