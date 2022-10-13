package designPatterns.creationalDesignPattern.single;
//https://medium.com/@kevalpatel2106/how-to-make-the-perfect-singleton-de6b951dfdb0
public class SingletonClass_Lazy {

    private static SingletonClass_Lazy singleton;
    //Private constructor
    private SingletonClass_Lazy() {}

    public static SingletonClass_Lazy getInstance() {
        if(singleton == null) {
            singleton = new SingletonClass_Lazy();
        }
        return singleton;
    }

}
