package objectOrientedProgramming.aggregation.example2;

import java.util.List;

// Course class having a list of students.
public class Course {

    String name;
    private List<Student> students;

    Course(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public List<Student> studentsData() {
        return students;
    }
}
