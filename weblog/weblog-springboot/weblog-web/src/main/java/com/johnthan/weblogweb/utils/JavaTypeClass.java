package com.johnthan.weblogweb.utils;

import com.johnthan.weblogweb.demos.web.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaTypeClass {

    public static void main(String[] args) {

        // 获取List
        List<String> stringList =  new ArrayList<>();
        List<Integer> integerList =  new ArrayList<>();
        List<User> userList =  new ArrayList<>();
        // 添加
        stringList.add("Java");
        stringList.add("Python");
        stringList.add("Javascript");

        // 数组转List
        String[] array = {"a", "b", "c"};
        List<String> list = Arrays.asList(array); // 固定大小List

        // List转数组
        String[] newArray = list.toArray(new String[0]);
        System.out.println("newArray"+newArray.toString());

    }
}
