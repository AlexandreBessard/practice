package designPatterns.creationalDesignPattern.factory;

public class PqrNetwork extends CellularPlan {

    @Override
    double getRate() {
        rate = 1.75;
        return rate;
    }
}
