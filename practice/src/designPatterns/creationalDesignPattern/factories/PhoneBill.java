package designPatterns.creationalDesignPattern.factories;

public class PhoneBill {

    public static void main(String[] args) {
        SelectNetworkFactory selectNetwork = new SelectNetworkFactory();
        CellularPlan network = selectNetwork.getPlan("abcNetwork");
        CellularPlan network1 = selectNetwork.getPlan("pqrNetwork");
        network.processBill(10);
        network1.processBill(10);
    }

}
