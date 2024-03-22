package uml.uniDirectionalAssociation;

/*
unidirectional association between classes A and B,
instances of class A can call methods of class B,
but instances of class B cannot call methods of class A.
 */

class B {
    void methodB() {
        System.out.println("Method B called from class A");
    }
}

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

}

public class Main {

    public static void main(String[] args) {
        A objA = new A();
        B objB = new B();
        // Setting reference of class B to class A
        objA.setB(objB);
        // Calling method of class B from class A
        objA.callBMethod(); // Class A calls method of class B
    }

}
