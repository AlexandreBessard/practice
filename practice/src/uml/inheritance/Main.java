package uml.inheritance;

/*
Inheritance: A inherits from B. A "is-a" B.
 */
class B {
    void methodB() {
        System.out.println("Method B from the superclass");
    }
}

class A extends B {
    void methodA() {
        System.out.println("Method A from subclass");
    }
}

public class Main {

    public static void main(String[] args) {
        A objA = new A();
        objA.methodA();
        objA.methodB();
    }

}
