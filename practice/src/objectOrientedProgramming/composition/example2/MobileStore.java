package objectOrientedProgramming.composition.example2;

import java.util.List;

public class MobileStore {
    private final List<Mobile> mobiles;

    MobileStore(List<Mobile> mobiles) {
        this.mobiles = mobiles;
    }

    public List<Mobile> getTotalMobileInStore() {
        return mobiles;
    }
}
