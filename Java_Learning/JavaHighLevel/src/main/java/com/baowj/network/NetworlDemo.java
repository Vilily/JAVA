/**
 * @author Bao WJ
 * @date 2021/11/30 16:26
 */
package com.baowj.network;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworlDemo {

    @Test
    public void test1 () throws UnknownHostException {
        InetAddress inet1 = InetAddress.getByName("www.baidu.com");
        System.out.println(inet1);
        System.out.println(InetAddress.getLocalHost());

        System.out.println(inet1.getCanonicalHostName());
        System.out.println(inet1.getHostAddress());
        System.out.println(inet1.getHostName());
    }

}
