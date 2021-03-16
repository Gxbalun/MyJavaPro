package TestDemo.fanshe;

public class TestClass1 {
    //Class类的对象理解
    //每一个被加载到内存中，都会创建一个Class的对象，来存储类的信息
    //反射的起点的就是获得类的Class对象

    public static void main(String[] args) throws ClassNotFoundException {

        //获得Class对象方式1
        Class uclass1 = Class.forName("TestDemo.fanshe.User");
        System.out.println(uclass1);

        //获得Class对象方式2
        Class uclass2 = User.class;
        System.out.println(uclass2);
        System.out.println(uclass1 == uclass2);

        //获得Class对象方式3
        User user1 = new User();
        User user2 = new User();
        Class uclass3 = user1.getClass();
        Class uclass4 = user2.getClass();
        System.out.println(uclass3);
        System.out.println(uclass4);
        System.out.println(uclass3 == uclass4);
        System.out.println(uclass1 == uclass4);

    }

}
