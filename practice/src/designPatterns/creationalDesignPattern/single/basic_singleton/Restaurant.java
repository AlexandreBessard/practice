package designPatterns.creationalDesignPattern.single.basic_singleton;
/*
It should be noted that many people dislike the Singleton design pattern, even calling it an "anti-pattern:'
One reason for this is that it can interfere with unit testing.
 */
public class Restaurant {

    private static Restaurant instance = null;
    protected Restaurant() {}
    public static Restaurant getInstance() {
        if(instance == null) {
            instance = new Restaurant();
        }
        return instance;
    }
}
