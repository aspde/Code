package thread_local;
/**使用场景：UserContextHolder里面保存了一个ThreadLocal，在调用Service1的方法的时候，就往里面存入user对象，而在后面去调用的时候，直接从里面用get方法取出来就可以了。没有参数层层传递的过程，非常的优雅、方便**/
public class ThreadLocalDemo2 {

    public static void main(String[] args) {
        new Service1().service1();
    }
}

class Service1 {

    public void service1() {
        User user = new User("方斌");
        UserContextHolder.holder.set(user);
        new Service2().service2();
    }
}

class Service2 {

    public void service2() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名：" + user.name);
        new Service3().service3();
    }
}

class Service3 {

    public void service3() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.name);
        UserContextHolder.holder.remove();
    }
}

class UserContextHolder {

    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {

    String name;

    public User(String name) {
        this.name = name;
    }
}
