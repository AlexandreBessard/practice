package objectOrientedProgramming.aggregation_HAS_A.example1;

public class StudentClass {

    int rollNum;
    String studentName;
    //Creating HAS-A relationship with Address class
    Address studentAddr;

    StudentClass(int roll, String name, Address addr) {
        this.rollNum = roll;
        this.studentName = name;
        this.studentAddr = addr;
    }
}
