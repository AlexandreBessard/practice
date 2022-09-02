package designPatterns.behavioralDesignPattern.strategyDesignPattern;

public class Query {

    private Calculator calculator;

    public Query(Calculator calculator) {
        this.calculator = calculator;
    }
    public float executeStrategy(float num1,
                                 float num2)
    {
        return calculator.calculation(num1, num2);
    }
}
