package uml.composition;

/*
Composition
Composition is a restricted form of Aggregation in which two entities are highly dependent on each other.

It represents part-of relationship.
In composition, both entities are dependent on each other.
When there is a composition between two entities, the composed object cannot exist without the other entity.
 */

// Engine class
class Engine {
    private String type;

    public Engine(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void start() {
        System.out.println("Engine started");
    }
}

// Type class
class Type {
    private String category;

    public Type(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

// Vehicle class
class Vehicle {
    private Engine engine;
    private Type type;

    public Vehicle(Engine engine, Type type) {
        this.engine = engine;
        this.type = type;
    }

    public void start() {
        engine.start();
        System.out.println("Vehicle of type " + type.getCategory() + " started");
    }
}

// Main class to demonstrate usage
public class Main2 {
    public static void main(String[] args) {
        // Creating an engine
        Engine engine = new Engine("V6");

        // Creating a type
        Type type = new Type("SUV");

        // Creating a vehicle with the engine and type
        Vehicle vehicle = new Vehicle(engine, type);

        // Starting the vehicle
        vehicle.start();
    }
}