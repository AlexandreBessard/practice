package designPatterns.creationalDesignPattern.builders.fluent_builder_inheritance;

public class Main {

    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder()
                .withName("Dmitri")
                .worksAs("Quantitative Analyst");
        System.out.println(eb.build());
    }

}
