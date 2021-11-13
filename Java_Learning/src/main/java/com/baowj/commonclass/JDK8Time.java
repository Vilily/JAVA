/**
 * @author Bao Wenjie
 */
package com.baowj.commonclass;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class JDK8Time {


    @Test
    public void test1() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localTime);
        System.out.println(localDate);
        System.out.println(localDateTime);
    }
}