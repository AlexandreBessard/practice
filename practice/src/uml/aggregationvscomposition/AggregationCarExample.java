package uml.aggregationvscomposition;

// Java Program to illustrate
// Aggregation

/*
In the above case of aggregation,
the Engine object is independent of the Car object
(i.e. Engine can exist without the Car object).
 */

// Class 1
// Engine class
class Engine {

    // attributes of Engine
    private String engineType;

    // Constructor
    public Engine(String engineType) {
        this.engineType = engineType;
    }

    public String getEngineType() {
        return engineType;
    }

    // Method to start the engine
    public void work()
    {
        // Print statement whenever this method is called
        System.out.println("Engine of car has been started");
    }
}

// Class 2
// Car class
class Car {

    // For a car to move,
    // it needs to have an engine.
    private Engine engine;

    // Constructor
    public Car(Engine engine)
    {
        this.engine = engine;   // Aggregation
    }

    // Method
    // Car start moving by starting engine
    public void move()
    {
        if (engine != null) {
            engine.work();
            System.out.println("Car is moving");
        }
        else {
            System.out.println("Car cannot start without engine");
        }
    }
}

// Class 3
// Main class
class AggregationCarExample {
    public static void main (String[] args) {

        // Creating an independent Engine Object
        Engine engine = new Engine("V10");

        // Creating a Car object, by passing the engine
        // such that, Car "has" Engine
        Car  car = new Car(engine);

        car.move();
        System.out.println("Engine Type: " + engine.getEngineType());
    }
}

