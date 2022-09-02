package designPatterns.structuralDesignPattern.AdapterDesignPattern;

public class Adapter {

    public static void main(String[] args) {
        LibraryCard targetInterface = new BookHolder();
        targetInterface.giveLibraryDetails();
        System.out.println(targetInterface.getLibraryCard());
    }

}
