package designPatterns.structuralDesignPattern.FacadeDesignPattern;

public class Dominos implements Franchise {

    @Override
    public void option() {
        System.out.println("Dominos");
    }

    @Override
    public void cost() {
        System.out.println("Rs 80,00,000");
    }
}
