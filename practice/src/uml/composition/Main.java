package uml.composition;

/*
Composition: A "has-an" instance of B.
B cannot exist without A
 */
// Class B
class B {
    // Field and constructor for class B
    private int value;

    // Constructor for class B
    public B(int value) {
        this.value = value;
    }

    // Method in class B
    public void display() {
        System.out.println("Value of B: " + value);
    }
}

// Class A
class A {
    // Field for class B
    private B b;

    // Constructor for class A that initializes class B
    public A(int value) {
        this.b = new B(value);
    }

    // Method in class A to use class B
    public void useB() {
        // Calling method of class B
        b.display();
    }

    // Method to destroy class A and consequently class B
    public void destroy() {
        System.out.println("Destroying object of class A and its contained object of class B");
        // Setting class B to null to release its reference
        this.b = null;
    }
}

// Main class to demonstrate usage
public class Main {
    public static void main(String[] args) {
        // Creating an instance of class A
        A objA = new A(10);

        // Calling method of class A to use class B
        objA.useB(); // This will display the value of B

        // Destroying object of class A
        objA.destroy();

        // Trying to call method of class B after destroying object of class A
        objA.useB(); // This will result in NullPointerException as class B does not exist anymore
    }
}
