package uml.aggregation;

/*
aggregation relationship, class A has an instance of class B,
but class B can exist independently of class A.
 */
class B {
    private int value;

    public B(int value) {
        this.value = value;
    }

    public void display() {
        System.out.println("Value of B : " + value);
    }

}

class A {
    private B b;

    public A(B b) {
        this.b = b;
    }

    public void useB() {
        b.display();
    }

}

public class Main {

    public static void main(String[] args) {
        // Creating an instance of class B
        B objB = new B(10);
        // Creating an instance of class A, passing class B as a parameter
        A objA = new A(objB);
        // Calling method of class A to use class B
        objA.useB(); // This will display the value of B
    }

}
