package designPatterns.creationalDesignPattern.factories;

public class PqrNetwork extends CellularPlan {

    @Override
    double getRate() {
        rate = 1.75;
        return rate;
    }
}
