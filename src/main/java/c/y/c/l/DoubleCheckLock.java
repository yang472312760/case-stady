package c.y.c.l;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:DoubleCheckLock</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/4 14:52</p>
 * <p>@Version:1.0</p>
 */
public class DoubleCheckLock {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton.getInstance();
            }).start();
        }
    }

}

class Singleton {

    private static Singleton instance;

    private Singleton() {
        System.out.println("instance 初始化");
    }

    public static Singleton getInstance() {

        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

}
