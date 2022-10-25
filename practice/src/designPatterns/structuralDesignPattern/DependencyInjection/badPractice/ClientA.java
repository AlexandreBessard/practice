package designPatterns.structuralDesignPattern.DependencyInjection.badPractice;

public class ClientA { //ClassA dependent on the class ServiceB
    ServiceB service; //dependency of ClientA
    public void doSomething() {
        String info = service.getInfo();
    }
}
//The code is inflexible

//The code is hard for unit testing because when you want to test only the functionalities of a class,
// you have to test other depending classes as well.

//The code is hard for reuse because the classes are tightly coupled