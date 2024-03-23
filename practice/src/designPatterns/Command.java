package designPatterns;

/*
The Command Pattern encapsulates a request as an object,
thereby allowing you to parameterize clients with queues,
requests, and operations. Here's an example demonstrating the Command Pattern

we have a simple home automation system where we can control
lights with commands such as turn on, turn off, and dim.
We'll implement these commands using the Command Pattern
 */

class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }
    public void turnOff() {
        System.out.println("Light is off");
    }
    public void dim() {
        System.out.println("Light is dimmed");
    }
}
// Command interface
interface CommandInterface {
    void execute();
}
// Concrete Command: TurnOnCommand
class TurnOnCommand implements CommandInterface {
    private Light light;
    public TurnOnCommand(Light light) {
        this.light = light;
    }
    @Override
    public void execute() {
        light.turnOn();
    }
}
// Concrete Command: TurnOffCommand
class TurnOffCommand implements CommandInterface {
    private Light light;

    public TurnOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}
// Concrete Command: DimCommand
class DimCommand implements CommandInterface {
    private Light light;

    public DimCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.dim();
    }
}
// Invoker: RemoteControl
class RemoteController {
    private CommandInterface command;

    public void setCommand(CommandInterface command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

public class Command {

    public static void main(String[] args) {
        // we demonstrate how to use the Command Pattern by creating commands
        // and associating them with the remote control to perform actions on the light
        Light light = new Light();

        CommandInterface turnOnCommand = new TurnOnCommand(light);
        CommandInterface turnOffCommand = new TurnOffCommand(light);
        CommandInterface dimCommand = new DimCommand(light);

        RemoteController remote = new RemoteController();

        // Turning on the light
        remote.setCommand(turnOnCommand);
        remote.pressButton();

        // Turning off the light
        remote.setCommand(turnOffCommand);
        remote.pressButton();

        // Dimming the light
        remote.setCommand(dimCommand);
        remote.pressButton();
    }

}
