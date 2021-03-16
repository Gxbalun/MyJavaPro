package TestDemo.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestClass2 {
    //Class类的对象理解
    //每一个被加载到内存中，都会创建一个Class的对象，来存储类的信息
    //反射的起点的就是获得类的Class对象

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        //获得Class对象
        String classname = "TestDemo.fanshe.User";
        Class uclass = Class.forName(classname);
        //User user = (User)uclass.newInstance();

        //从Class独享中获得类的相关信息，构造方法，成员变量，成员方法

        //获得的是默认无参的构造方法(公共)
        //Constructor constructor = uclass.getConstructor();
        //User user = (User)constructor.newInstance();
        //System.out.println(user);

        //获得的是有两个参数的构造方法(公共)
        /*Constructor constructor = uclass.getConstructor(int.class,String.class);
        User user = (User)constructor.newInstance(22,"小马");
        System.out.println(user);*/

        //获得的是默认无参的构造方法(私有的也可以)
        /*Constructor constructor = uclass.getDeclaredConstructor(String.class,int.class);
        constructor.setAccessible(true);//设置访问权限
        User user = (User)constructor.newInstance("小马",22);
        System.out.println(user);*/

        Constructor[] declaredConstructors = uclass.getDeclaredConstructors();
        for (Constructor con : declaredConstructors){
            con.setAccessible(true);

        }
    }

}
