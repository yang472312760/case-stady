package c.y.c.thread;


import java.util.concurrent.atomic.AtomicReference;

public class VtomicReferenceDemo {

    public static void main(String[] args) {


        User z3 = new User("z3", 45);
        User lisi = new User("lisi", 23);

        AtomicReference<User> atomicReference = new AtomicReference<>();

        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, lisi) + "\t " + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, lisi) + "\t " + atomicReference.get().toString());


    }

}


class User {

    String userName;

    int age;

    public User() {
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
