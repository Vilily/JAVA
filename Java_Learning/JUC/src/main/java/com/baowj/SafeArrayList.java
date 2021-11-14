/**
 * @author Bao Wenjie
 * @date 2021/11/13
 */

package com.baowj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class SafeArrayList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        CopyOnWriteArrayList<Integer> list1 = new CopyOnWriteArrayList<>();

        Vector<Integer> list2 = new Vector<>();
    }
}