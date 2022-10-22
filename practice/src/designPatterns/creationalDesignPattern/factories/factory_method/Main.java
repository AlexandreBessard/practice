package designPatterns.creationalDesignPattern.factories.factory_method;

public class Main {

    public static void main(String[] args) {
        var notificationFactory = new NotificationFactory();
        var notification = notificationFactory.createNotification(NotificationType.SMS);
        notification.notifyUser();
    }

}
