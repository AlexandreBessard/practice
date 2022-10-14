package designPatterns.creationalDesignPattern.factories;

public abstract class CellularPlan {
    protected double rate;
    abstract double getRate();
    public void processBill(int minutes) {
        System.out.println(minutes * getRate());
    }
}
