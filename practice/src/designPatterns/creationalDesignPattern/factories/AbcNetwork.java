package designPatterns.creationalDesignPattern.factories;

public class AbcNetwork extends CellularPlan {

    @Override
    double getRate() {
        this.rate = 1.50;
        return rate;
    }
}
