package designPatterns.behavioralDesignPattern.strategyDesignPattern;

public class Div implements Calculator {

    @Override
    public float calculation(float a, float b) {
        return a / b;
    }
}
