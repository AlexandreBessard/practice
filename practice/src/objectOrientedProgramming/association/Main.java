package objectOrientedProgramming.association;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //1 person can have multiple phone numbers -> one to many relationship
        Person person = new Person();
        person.name = "Alex";

        Mobile number1 = new Mobile();
        number1.mobile_no = "0546586214";
        Mobile number2 = new Mobile();
        number2.mobile_no = "44521455";

        person.numbers = new ArrayList<>(List.of(number1, number2));
        System.out.println(person.name + " has " + person.numbers.size() + " phone numbers.");
    }
}
