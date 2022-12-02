package designPatterns.creationalDesignPattern.factories.factory_method;

//Create factory class to instantiate concrete class
public class NotificationFactory {

    public Notification createNotification(NotificationType channel) {
        if (channel == null) return null;
        Notification notification;
        switch (channel) { //Equivalent of a switch
            case SMS:
                notification = new SMSNotification();
                break;
            case EMAIL:
                notification = new EmailNotification();
                break;
            case PUSH:
                notification = new PushNotification();
                break;
            default:
                throw new IllegalStateException("Invalid");
            //default -> throw new IllegalArgumentException("Unknown channel " + channel);
        };
        return notification;
    }
}
