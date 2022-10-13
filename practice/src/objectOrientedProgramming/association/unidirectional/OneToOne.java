package objectOrientedProgramming.association.unidirectional;

public class OneToOne {

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setName("John");
        Person p2 = new Person();
        p2.setName("Shubh");

        Passport pp1 = new Passport();
        pp1.setPassportNo(1234567);
        Passport pp2 = new Passport();
        pp2.setPassportNo(12398576);

        // Association between two classes in the main method.
        //One to One relationship
        System.out.println(p1.getName()+ " has a US passport whose passport no is: " +pp1.getPassportNo());
        System.out.println(p2.getName()+ " has an Indian passport whose passport no is: " +pp2.getPassportNo());
    }

}
