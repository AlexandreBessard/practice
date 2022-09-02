package designPatterns.jeeDesignPattern.daoDesignPattern;

public class Developer {
    private String name;
    private int DeveloperId;
    Developer(String name, int DeveloperId){
        this.name = name;
        this.DeveloperId = DeveloperId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getDeveloperId(){
        return DeveloperId;
    }
    public void setDeveloperId(int DeveloperId){
        this.DeveloperId = DeveloperId;
    }
}
