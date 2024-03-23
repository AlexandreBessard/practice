package designPatterns;

/*
This example demonstrates how the Abstract Factory Pattern can be used to
create different types of vehicles without exposing the instantiation logic to the client code.
 */
interface Vehicle {
    void assemble();
}

class Car implements Vehicle {
    @Override
    public void assemble() {
        System.out.println("Assembling a car.");
    }
}

class Motorcycle implements Vehicle {
    @Override
    public void assemble() {
        System.out.println("Assembling a motorcycle.");
    }
}

// Abstract Factory: Vehicle Factory
interface VehicleFactory {
    Vehicle createVehicle();
}

class CarFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}
// Concrete Factory: Motorcycle Factory
class MotorcycleFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Motorcycle();
    }
}

// Client
public class AbstractFactory {

    private VehicleFactory factory;

    public AbstractFactory(VehicleFactory factory) {
        this.factory = factory;
    }

    public void assembleVehicle() {
        Vehicle vehicle = factory.createVehicle();
        vehicle.assemble();
    }

    public static void main(String[] args) {
        AbstractFactory carClient = new AbstractFactory(new CarFactory());
        carClient.assembleVehicle();
        AbstractFactory motorCycleClient = new AbstractFactory(new MotorcycleFactory());
        motorCycleClient.assembleVehicle();
    }
}
