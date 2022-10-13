package objectOrientedProgramming.association.bidirectional;

public class ManyToMany {

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setName("John");
        Person p2 = new Person();
        p2.setName("Shubh");

        Address a1 = new Address();
        a1.setState("Jharkhand");
        a1.setCity("Dhanbad");
        a1.setZip("123524");
        Address a2 = new Address();
        a2.setState("Maharashtra");
        a2.setCity("Mumbai");
        a2.setZip("123635");

        // Association between two classes in the main method.
        //In this example program, the association between Person and Address
        // is accomplished in the main method and shows a bidirectional many-to-many relationship.
        System.out.println(p1.getName()+ " lives at address " +a1.getCity()+ "' " +a1.getState()+ ", " +a1.getZip()+ " but he has also address at " +a2.getCity()+ ", " +a2.getState()+ ", "+a2.getZip());
        System.out.println(p2.getName()+ " lives at address " +a2.getCity()+ "' " +a2.getState()+ ", " +a2.getZip()+ " but she has also address at " +a1.getCity()+ ", " +a1.getState()+ ", "+a1.getZip());
    }

}
