package com.qf.webpro.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassDemo2 {

    public static Field getField(String name) throws ClassNotFoundException, NoSuchFieldException {

        Field field = Class.forName(name).getDeclaredField("isRestricted");
        field.getName();
        return field;
    }
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        String s = "com.mysql.cj.jdbc.Driver";
        String s2 = "com.qf.webpro.Test.User";
        Object o = Class.forName(s);
        //System.out.println();
        System.out.println(getField(s2));

    }
}
