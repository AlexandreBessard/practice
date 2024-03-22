package uml.generalization;

/*
generalization relationship between classes using Java code.
In a generalization relationship, a subclass inherits properties and behaviors
from a superclass. In Java, this is typically achieved using the extends keyword
for classes and the implements keyword for interfaces.
 */
interface B {
    void methodB();
}

class A implements B {

    @Override
    public void methodB() {
        System.out.println("Method B implemented in class A");
    }
}
public class Main {

    public static void main(String[] args) {
        A objA = new A();
        objA.methodB();
    }

}
