package objectOrientedProgramming.aggregation_HAS_A.example2;

import java.util.List;

/* College class having a list of Courses*/
public class College {
    String collegeName;
    private List<Course> courses;

    College(String collegeName, List<Course> courses) {
        this.collegeName = collegeName;
        this.courses = courses;
    }

    // Returning number of students available in all courses in a given college
    public int countStudents() {
        int studentsInCollege = 0;
        List<Student> students;
        for (Course course : courses) {
            students = course.studentsData();
            for (Student s : students) {
                studentsInCollege++;
            }
        }
        return studentsInCollege;
    }


}
