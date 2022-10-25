package designPatterns.structuralDesignPattern.Facade;

public class KFC implements Franchise {

    @Override
    public void option() {
        System.out.println("KFC");
    }

    @Override
    public void cost() {
        System.out.println("Rs 1,00,00,000");
    }
}
