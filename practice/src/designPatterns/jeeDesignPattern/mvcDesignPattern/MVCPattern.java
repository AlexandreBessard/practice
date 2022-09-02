package designPatterns.jeeDesignPattern.mvcDesignPattern;

public class MVCPattern {

    public static void main(String[] args){
        Student model = retriveStudentFromDatabase();
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();
        controller.setStudentName("Sandeep Shukla");
        controller.updateView();
    }
    private static Student retriveStudentFromDatabase(){
        Student student = new Student();
        student.setName("Karan Kumar");
        student.setRollNo("21CSE987");
        return student;
    }

}
