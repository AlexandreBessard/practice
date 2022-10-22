package designPatterns.creationalDesignPattern.factories.factory_method;

public class PushNotification implements Notification {

    @Override
    public void notifyUser() {
        System.out.println("Sending an push notification");
    }
}
