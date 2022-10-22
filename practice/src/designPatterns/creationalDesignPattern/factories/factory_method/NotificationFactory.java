package designPatterns.creationalDesignPattern.factories.factory_method;

//Create factory class to instantiate concrete class
public class NotificationFactory {

    public Notification createNotification(NotificationType channel) {
        if (channel == null) return null;
        return switch (channel) { //Equivalent of a switch
            case SMS -> new SMSNotification();
            case EMAIL -> new EmailNotification();
            case PUSH -> new PushNotification();
            //default -> throw new IllegalArgumentException("Unknown channel " + channel);
        };
    }
}
