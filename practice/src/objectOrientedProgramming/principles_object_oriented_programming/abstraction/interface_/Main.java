package objectOrientedProgramming.principles_object_oriented_programming.abstraction.interface_;
//https://www.javatpoint.com/interface-in-java
public class Main {

    public static void main(String[] args) {
        Bank b = new SBI();
        System.out.println(b.rateOfInterest());
    }

}
