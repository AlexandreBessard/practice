package designPatterns.structuralDesignPatterns.FacadeDesignPattern;

public class Client {

    public static void main(String[] args) {
        var franchiseServiceReg = new FranchiseServiceReg();
        int choice  = 1;
        switch (choice) {
            case 1: {
                franchiseServiceReg.buyKFCFranchise();
            } break;
            case 2: {
                franchiseServiceReg.BuyDominosFranchise();
            } break;
            case 3: {
                franchiseServiceReg.BuyMcDonaldsFranchise();
            } break;
            default: {
                System.out.println("Incorrecte");
            }
        }
    }

}
