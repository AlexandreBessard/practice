package designPatterns;
/*
Bridge Pattern in a scenario where we have different
types of devices (e.g., TV, Radio) and different types of
remote controls (e.g., basic remote, advanced remote).
The Bridge Pattern allows us to independently vary the types of
devices and remote controls
 */

interface Device {
    void turnOn();
    void turnOff();
    void setChannel(int channel);
}
// Concrete Implementor 1: TV
class TV implements Device {
    private boolean isOn = false;
    private int channel = 1;

    @Override
    public void turnOn() {
        System.out.println("TV is turned on");
        isOn = true;
    }

    @Override
    public void turnOff() {
        System.out.println("TV is turned off");
        isOn = false;
    }

    @Override
    public void setChannel(int channel) {
        if (isOn) {
            this.channel = channel;
            System.out.println("Set TV channel to " + channel);
        } else {
            System.out.println("Cannot set channel. TV is turned off");
        }
    }
}
// Concrete Implementor 2: Radio
class Radio implements Device {
    private boolean isOn = false;
    private int channel = 1;

    @Override
    public void turnOn() {
        System.out.println("Radio is turned on");
        isOn = true;
    }

    @Override
    public void turnOff() {
        System.out.println("Radio is turned off");
        isOn = false;
    }

    @Override
    public void setChannel(int channel) {
        if (isOn) {
            this.channel = channel;
            System.out.println("Set radio channel to " + channel);
        } else {
            System.out.println("Cannot set the channel. Radio is turned off");
        }
    }
}

// Abstraction: RemoteControl
abstract class RemoteControl {
    protected Device device;
    protected RemoteControl(Device device) {
        this.device = device;
    }
    abstract void powerOn();
    abstract void powerOff();
    abstract void setChannel(int channel);
}

// Refined Abstraction 1: Basic RemoteControl
class BasicRemoteControl extends RemoteControl {
    public BasicRemoteControl(Device device) {
        super(device);
    }
    @Override
    void powerOn() {
        device.turnOn();
    }

    @Override
    void powerOff() {
        device.turnOff();
    }

    @Override
    void setChannel(int channel) {
        device.setChannel(channel);
    }
}
// Refined Abstraction 2: Advanced RemoteControl
class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    @Override
    void powerOn() {
        device.turnOn();
        System.out.println("Switched on with the advanced settings");
    }

    @Override
    void powerOff() {
        device.turnOff();
        System.out.println("Switched off with the advanced settings");
    }

    @Override
    void setChannel(int channel) {
        device.setChannel(channel);
        System.out.println("Channel set to " + channel + " with advanced settings");
    }
}

public class Bridge {

    public static void main(String[] args) {
        // Demonstrating the independence between devices and remote controls facilitated by the Bridge Pattern.

        Device tv = new TV();
        RemoteControl basicRemoteControl = new BasicRemoteControl(tv);
        basicRemoteControl.powerOn();
        basicRemoteControl.setChannel(5);
        basicRemoteControl.powerOff();

        Device radio = new Radio();
        RemoteControl advancedRemote = new AdvancedRemoteControl(radio);
        advancedRemote.powerOn();
        advancedRemote.setChannel(10);
        advancedRemote.powerOff();
    }

}
