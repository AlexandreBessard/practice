package designPatterns.creationalDesignPatterns.SingleDesignPattern;
//https://medium.com/@kevalpatel2106/how-to-make-the-perfect-singleton-de6b951dfdb0
public class SingletonClass_Eager {

    private static volatile SingletonClass_Eager singleton =
            new SingletonClass_Eager();

    //private constructor
    private SingletonClass_Eager() {}

    public static SingletonClass_Eager getInstance() {
        return singleton;
    }
}
