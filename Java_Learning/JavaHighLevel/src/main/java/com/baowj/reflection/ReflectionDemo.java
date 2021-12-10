/**
 * @author Bao WJ
 * @date 2021/11/30 19:46
 */
package com.baowj.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {

    @Test
    public void test1() {
        Person p1 = new Person("Tom", 18);
        p1.age = 90;
        p1.show();
    }

    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 反射创建类
        Class cla = Person.class;
        Constructor constructor = cla.getConstructor(String.class, int.class);
        Object p1 = constructor.newInstance("Tom", 17);
        Person person = (Person) p1;
        person.show();

        // 反射调用指定属性
        Field age = cla.getDeclaredField("age");
        age.set(person, 10);
        person.show();

        // 反射调用指定方法
        Method show = cla.getDeclaredMethod("show");
        show.invoke(person);

        // 反射调用私有构造器
        Constructor declaredConstructor = cla.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object jerry = declaredConstructor.newInstance("Jerry");
        ((Person)jerry).show();

        // 反射调用私有属性
        Field name = cla.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, "KKKKKK");
        person.show();

    }

    @Test
    public void test3() throws ClassNotFoundException {
        Class cla1 = Person.class;
        System.out.println(cla1);

        Person person = new Person();
        Class cla2 = person.getClass();
        System.out.println(cla2);

        Class cla3 = Class.forName("com.baowj.reflection.Person");
        System.out.println(cla3);
    }

    @Test
    public void test4() throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class clazz = Person.class;
        Object o = clazz.newInstance();
        System.out.println(o);
        Method declaredMethod = clazz.getDeclaredMethod("");

    }

}
