package designPatterns.creationalDesignPattern.FactoryDesignPatterns;

public class XyzNetwork extends CellularPlan {

    @Override
    double getRate() {
        this.rate = 1.50;
        return rate;
    }
}
