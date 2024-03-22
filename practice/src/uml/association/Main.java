package uml.association;

/*
Association: A and B call each other.
 */
class A {
    private B b;

    void callBMethod() {
        if (b != null) {
            b.methodB();
        }
    }

    void setB(B b) {
        this.b = b;
    }

    void methodA() {
        System.out.println("Calling methodA from class A");
    }
}

class B {

    private A a;

    void callAMethod() {
        if (a != null) {
            a.methodA();
        }
    }

    void setA(A a) {
        this.a = a;
    }

    void methodB() {
        System.out.println("Calling methodB from B class");
    }
}

public class Main {

    public static void main(String[] args) {
        A objA = new A();
        B objB = new B();
        // Setting references to each other
        objA.setB(objB);
        objB.setA(objA);
        // Calling methods between class A and class B
        objA.callBMethod(); // Class A calls method in class B
        objB.callAMethod(); // Class B calls method in class A
    }

}
