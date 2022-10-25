package designPatterns.structuralDesignPattern.DecoratorDesignPattern;
//Decorator Design pattern
public class Main {

    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape redCircle = new RedShapeDecorator(new Circle()); //Add new functionalities to the object Circle
        Shape redRectangle = new RedShapeDecorator(new Rectangle()); //Add new functionalities to the object Rectangle

        //circle.draw();
        redCircle.draw();
        redRectangle.draw();
    }

}
