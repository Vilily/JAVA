/**
 * @author Bao WJ
 * @date 2021/12/2 20:59
 */
package com.baowj.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;

public class UDPServerDemo {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9999);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);

        socket.receive(datagramPacket);

        socket.close();
    }
}
