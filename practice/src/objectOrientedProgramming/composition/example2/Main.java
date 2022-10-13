package objectOrientedProgramming.composition.example2;

import java.util.ArrayList;
import java.util.List;
/*
We create a class Mobile that contains variables, i.e., name, ram and rom.
We also create a class MobileStore that has a reference to refer to the list of mobiles.
A mobile store can have more than one mobile. So, if a mobile store is destroyed,
then all mobiles within that particular mobile store will also be destroyed because mobiles cannot exist without a mobile store.
The relationship between the mobile store and mobiles is Composition.
 */
public class Main {

    public static void main(String[] args) {
        MobileStore store = new MobileStore(createMobiles());
        for(Mobile mobile : store.getTotalMobileInStore()) {
            System.out.println("Name: " + mobile.name + " RAM: " + mobile.ram + " ROM: " + mobile.ram);
        }
    }

    static List<Mobile> createMobiles() {
        Mobile mob1 = new Mobile("IPHONE","8GB", "128GB");
        Mobile mob2 = new Mobile("SAMSUNG A21S", "4GB", "128");
        Mobile mob3 = new Mobile("SAMSUNG M10", "4GB", "64GB");
        return new ArrayList<>(List.of(mob1, mob2, mob3));
    }

}
