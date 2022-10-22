package objectOrientedProgramming.aggregation_HAS_A.example1;

public class Main {

    public static void main(String[] args) {
        Address ad = new Address(55, "Agra", "UP", "India");
        StudentClass obj = new StudentClass(123, "Chaitanya", ad);
        /*
        The below example shows the Aggregation between Student and Address classes. You
        can see that in Student class I have declared a property of type Address to obtain student address.
        Its a typical example of Aggregation in Java.
         */
        System.out.println(obj.studentAddr.streetNum);
    }

}
