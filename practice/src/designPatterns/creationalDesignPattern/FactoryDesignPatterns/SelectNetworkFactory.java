package designPatterns.creationalDesignPattern.FactoryDesignPatterns;

public class SelectNetworkFactory {

    public CellularPlan getPlan(String planType) {
        if(planType == null) {
            return null;
        }
        if(planType.equalsIgnoreCase("abcNetwork")) {
            return new AbcNetwork();
        }
        else if(planType.equalsIgnoreCase("xyzNetwork")) {
            return new XyzNetwork();
        }
        else if(planType.equalsIgnoreCase("pqrNetwork")) {
            return new PqrNetwork();
        }
        return null;
    }
}
