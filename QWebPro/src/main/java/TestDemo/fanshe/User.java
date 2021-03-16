package TestDemo.fanshe;

public class User {
    public int num;
    private String name;

    public User(){
        System.out.println("无参构造");
    }

    public User(int num){
        this.num = num;
        System.out.println("一个参数构造");
    }

    public User(int num, String name) {
        this.num = num;
        this.name = name;
        System.out.println("两个参数构造");
    }

    private User(String name,int num) {
        this.num = num;
        this.name = name;
        System.out.println("私有的构造");
    }

    public void eat(){
        System.out.println("无参eat方法");
    }

    public void eat(String a){
        System.out.println("一个参数的eat方法");
    }

    public void eat(int a,String b){
        System.out.println("两个参数的eat方法");
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
