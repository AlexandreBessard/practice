package objectOrientedProgramming.aggregation_HAS_A.example2;

import java.util.ArrayList;
import java.util.List;

public class Main {
/*
In the above example, there is a college that has several courses like BSC-CS, MCA, and Poly.
Every course has several students, so we make a College class that has a reference to the object
or list of objects of the Course class. That means College class is associated with Course
class through the objects. Course class also has a reference to the object or list of objects of
Student class means it is associated with Student class through its object and defines the HAS-A relationship.
 */
    public static void main(String[] args) {
        List<Student> students = createStudents();
        // Constructing list of MCA Students.
        List <Student> mca_students = new ArrayList<>();
        mca_students.add(students.get(0));
        mca_students.add(students.get(3));

        //Constructing list of BSC-CS Students.
        List <Student> bsc_cs_students = new ArrayList<>();
        bsc_cs_students.add(students.get(1));

        //Constructing list of Poly Students.
        List <Student> poly_students = new ArrayList<>();
        poly_students.add(students.get(2));
        poly_students.add(students.get(4));

        Course MCA = new Course("MCA", mca_students);
        Course BSC_CS = new Course("BSC-CS", bsc_cs_students);
        Course Poly = new Course("Poly", poly_students);

        List <Course> courses = new ArrayList<>();
        courses.add(MCA);
        courses.add(BSC_CS);
        courses.add(Poly);

        // creating object of College.
        College college = new College("ABES", courses);
        System.out.print("Total number of students in the college "+ college.collegeName +" is "+ college.countStudents());

    }

    static List<Student> createStudents() {
        Student std1 = new Student("Emma", 1801, "MCA");
        Student std2 = new Student("Adele", 1802, "BSC-CS");
        Student std3 = new Student("Aria", 1803, "Poly");
        Student std4 = new Student("Ally", 1804, "MCA");
        Student std5 = new Student("Paul", 1805, "Poly");
        return new ArrayList<>(List.of(std1, std2, std3, std4, std5));
    }

}
