/**
 * @author Bao WJ
 * @date 2021/12/3 15:45
 */
package com.baowj.reflection;

import java.lang.reflect.Method;

public class Test {
    static {
        System.out.println("Main类被加载");
    }

    public static void main(String[] args) {
        // AppClassLoader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        // ExtClassLoader
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
        // AppClassLoader
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        Method a;
    }
}

class Father {
    static {
        System.out.println("父类被加载");
    }

    static int b = 2;
}

class Son extends Father {
    static {
        System.out.println("子类被加载");
    }
    static int m = 100;
    static final int M = 1;
}
