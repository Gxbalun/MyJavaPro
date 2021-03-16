package com.qf.webpro.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class classDemo {


    public static void main(String[] args) throws ClassNotFoundException {
        String s1 = "com.mysql.cj.jdbc.Driver";

        Object s = Class.forName(s1);
        System.out.println(s);


    }
}
