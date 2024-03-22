package uml.useInterface;

interface B {
    void methodB();
}

// Class A using interface B
class A {
    B b;

    // Constructor to inject an object that implements interface B
    A(B b) {
        this.b = b;
    }

    void useB() {
        b.methodB();
    }
}

// Class implementing interface B
class ImplementerB implements B {

    @Override
    public void methodB() {
        System.out.println("Method B implemented in ImplementerB");
    }
}

// Main class to demonstrate usage
public class Main {

    public static void main(String[] args) {
        ImplementerB implementerB = new ImplementerB();
        A objA = new A(implementerB);
        objA.useB();
    }

}
