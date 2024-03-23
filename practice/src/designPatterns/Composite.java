package designPatterns;

/*
The Composite Pattern allows you to compose objects
into tree structures to represent part-whole hierarchies.
Here's an example demonstrating the Composite Pattern.

Let's consider a scenario of representing a company's
organizational hierarchy, which can consist of individual
employees or other sub-teams
 */

import java.util.ArrayList;
import java.util.List;

// Component: Component
interface Employee {
    void showDetails();
}
// Leaf: Individual Employee
class IndividualEmployee implements Employee {
    private String name;
    private String position;

    public IndividualEmployee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public void showDetails() {
        System.out.println("Name: " + name + ", Position: " + position);
    }
}
// Composite: Team
class Team implements Employee {
    private String name;
    private List<Employee> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addMember(Employee member) {
        members.add(member);
    }

    public void removeMember(Employee member) {
        members.remove(member);
    }

    @Override
    public void showDetails() {
        System.out.println("Team: " + name);
        for (Employee member : members) {
            member.showDetails();
        }
    }
}

public class Composite {

    public static void main(String[] args) {
        //we demonstrate how to create individual employees,
        // teams, and compose them into a hierarchical structure
        // to represent a company's organizational chart.
        // Then, we display the details of the entire company hierarchy.

        // Individual Employees
        Employee john = new IndividualEmployee("John Doe", "Software Engineer");
        Employee jane = new IndividualEmployee("Jane Smith", "Product Manager");
        Employee alice = new IndividualEmployee("Alice Johnson", "Marketing Specialist");

        // Team
        Team engineeringTeam = new Team("Engineering Team");
        engineeringTeam.addMember(john);
        engineeringTeam.addMember(jane);

        Team marketingTeam = new Team("Marketing Team");
        marketingTeam.addMember(alice);

        // Company Hierarchy
        Team company = new Team("XYZ Corp");
        company.addMember(engineeringTeam);
        company.addMember(marketingTeam);

        // Display company hierarchy
        company.showDetails();
    }

}
