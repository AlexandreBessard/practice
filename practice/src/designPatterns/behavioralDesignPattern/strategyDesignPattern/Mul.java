package designPatterns.behavioralDesignPattern.strategyDesignPattern;

public class Mul implements Calculator {

    @Override
    public float calculation(float a, float b) {
        return a * b;
    }
}
