package TestDemo.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class TestClass3 {
    //Class类的对象理解
    //每一个被加载到内存中，都会创建一个Class的对象，来存储类的信息
    //反射的起点的就是获得类的Class对象

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {

        //获得Class对象
        String classname = "TestDemo.fanshe.User";
        Class uclass = Class.forName(classname);

        //Object obj = uclass.newInstance();
        User user = (User)uclass.newInstance();

       /* Field namefield = uclass.getDeclaredField("name");//获得指定的公共的成员变量
        namefield.setAccessible(true);
        Field numfield = uclass.getField("num");
        numfield.set(user,21);
        namefield.set(user,"小马");*/

        Field[] fs = uclass.getDeclaredFields();
        for(Field f : fs){
            f.setAccessible(true);
            System.out.println("set"+f.getName());
        }

        System.out.println(user);
    }

}
