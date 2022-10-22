package objectOrientedProgramming.principles_object_oriented_programming.abstraction.abstract_class;
//https://www.javatpoint.com/abstract-class-in-java
public class Main {

    public static void main(String[] args) {
        Bank b;
        b = new SBI();
        System.out.println(b.getRateOfInterest());

        b = new PNB();
        System.out.println(b.getRateOfInterest());
    }

}
