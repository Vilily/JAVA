/**
 * @author Bao WJ
 * @date 2021/12/2 20:55
 */
package com.baowj.network;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class UDPClientDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress inet = InetAddress.getLocalHost();
        int port = 9999;
        String msg = "Hello";
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, inet, port);

        socket.send(packet);

        socket.close();
    }
}
