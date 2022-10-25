package designPatterns.structuralDesignPattern.DependencyInjection;
/*
Dependency injection is a technique that allows the client code to be independent from the services it is relying on.
The client does not control how objects of the services are created - it works with an implementation of the service through interface
 */
public class Main {

    public static void main(String[] args) {
        Service serviceB = new ServiceB();
        Client client = new ClientA(serviceB);
        client.doSomething();
        //Then we can change to different Service’s implementation e.g. ServiceC like this:
        ((ClientA)client).setService(new ServiceC());
        client.doSomething();
    }

}
