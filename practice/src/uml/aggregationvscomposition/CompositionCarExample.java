package uml.aggregationvscomposition;

// Java Program to Illustrate
// Composition

// Importing I/O classes

/*
In the above case of composition, the Car owns the Engine object
(i.e. Engine cannot exist without the Car object).
 */

// Class 1
// Engine class which will be used by car.
class Engine1 {

    // Method to start the engine
    public void work()
    {
        // Print statement whenever this method is called
        System.out.println(
                "Engine of car has been started ");
    }
}

// Class 2
// Car class
class Car1 {

    // For a car to move,
    // it needs to have an engine.
    private final Engine1 engine;

    // Constructor of this class
    public Car1()
    {
        this.engine = new Engine1();   // Composition
    }

    // Method
    // Car start moving by starting engine
    public void move()
    {
        engine.work();
        System.out.println("Car is moving");
    }
}

// Class 3
// Main class
class CompositionCarExample {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating a car object,
        // which will also initializes the engine class object
        Car1 car = new Car1();

        // Making car to move by calling
        // move() method inside main()
        car.move();
    }
}

