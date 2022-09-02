package designPatterns.jeeDesignPattern.daoDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {

    List<Developer> Developers;

    public DeveloperDaoImpl(){
        Developers = new ArrayList<Developer>();
        Developer Developer1 = new Developer("Kiran",101011);
        Developer Developer2 = new Developer("Vikrant",124122);
        Developers.add(Developer1);
        Developers.add(Developer2);
    }
    public void deleteDeveloper(Developer Developer){
        Developers.remove(Developer.getDeveloperId());
        System.out.println("DeveloperId " + Developer.getDeveloperId() + ", deleted from database");
    }
    public List<Developer> getAllDevelopers(){
        return Developers;
    }
    public Developer getDeveloper(int DeveloperId){
        return Developers.get(DeveloperId);
    }
    public void updateDeveloper(Developer Developer){
        Developers.get(Developer.getDeveloperId()).setName(Developer.getName());
        System.out.println("DeveloperId " + Developer.getDeveloperId() + ", updated in the database");
    }
}
