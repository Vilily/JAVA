/**
 * @author Bao WJ
 * @date 2021/12/1 17:00
 */
package com.baowj.stream;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {

    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        Stream<Integer> stream = list.stream();
    }

    @Test
    public void test2() {
        int[] arr = new int[10];
        IntStream stream = Arrays.stream(arr);
    }

    @Test
    public void test3() {
        Stream<? extends Serializable> cuffed = Stream.of(1, 2.0, 3, 4, "hahaha");

    }

    @Test
    public void test4() {
        Stream.iterate(0,(t) -> t + 2).limit(10).forEach(System.out::println);
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }


    @Test
    public void test5() {
        Stream.iterate(0,(t) -> t + 2).limit(10).forEach(System.out::println);
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
