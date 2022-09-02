package designPatterns.structuralDesignPattern.FacadeDesignPattern;

public class McDonalds implements Franchise {

    @Override
    public void option() {
        System.out.println("McDonalds");
    }

    @Override
    public void cost() {
        System.out.println("Rs 90,00,000");
    }
}
