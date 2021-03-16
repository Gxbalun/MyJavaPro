package TestDemo.fanshe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClass5 {
    //Class类的对象理解
    //每一个被加载到内存中，都会创建一个Class的对象，来存储类的信息
    //反射的起点的就是获得类的Class对象

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {

        //获得Class对象
        String classname = "TestDemo.fanshe.User";
        Class uclass = Class.forName(classname);

        //Object obj = uclass.newInstance();
        User user = (User)uclass.newInstance();

        Field[] fs = uclass.getDeclaredFields();
        for(Field f :fs){
            String name = f.getName();
            Method getm = uclass.getDeclaredMethod("get"+String.valueOf(name.charAt(0)).toUpperCase()+name.substring(1));
            System.out.println(getm.invoke(user));
        }
    }

}
