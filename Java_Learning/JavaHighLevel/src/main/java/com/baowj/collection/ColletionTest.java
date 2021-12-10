/**
 * @author Bao WJ
 * @date 2021/11/16
 */
package com.baowj.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ColletionTest {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(123);
        list.add("gfdg");
        List tmp = new ArrayList();
        tmp.add(899);
        list.add(tmp);
        System.out.println(list);

    }
}