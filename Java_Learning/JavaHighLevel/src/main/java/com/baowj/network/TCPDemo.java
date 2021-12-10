/**
 * @author Bao WJ
 * @date 2021/11/30 16:43
 */
package com.baowj.network;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPDemo {

    @Test
    public void client() throws IOException {
        InetAddress inet = InetAddress.getLocalHost();
        Socket socket = new Socket(inet, 8899);
        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("Hello World".getBytes());
        outputStream.close();
        socket.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);

        Socket socket = serverSocket.accept();

        InputStream is = socket.getInputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[20];
        int len;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        System.out.println(baos.toString());

        System.out.println(socket.getInetAddress().getHostAddress());
        baos.close();
        is.close();
        socket.close();
        serverSocket.close();
    }


}
