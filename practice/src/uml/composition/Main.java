package uml.composition;

/*
composition relationship, class A contains an instance of class B,
and the existence of class B is tightly bound to class A
 */
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

class A {
    private B b;

    public A(int value) {
        this.b = new B(value);
    }

    public void useB() {
        b.display();
    }

}


public class Main {

    public static void main(String[] args) {
        A objA = new A(10);
        objA.useB();
    }

}
