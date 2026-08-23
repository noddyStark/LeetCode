package CompanyWise.BNSF;

import java.util.ArrayList;
import java.util.List;

public class LocoMotivesAndRailCars {

    static void main() {

        Locomotives l1 = new Locomotives("L1", 1000);
        Locomotives l2 = new Locomotives("L2", 2000);
        Locomotives l3 = new Locomotives("L3", 1500);
        Locomotives l4 = new Locomotives("L4", 1200);
        Locomotives l5 = new Locomotives("L5", 1700);
        Locomotives l6 = new Locomotives("L6", 1900);
        Locomotives l7 = new Locomotives("L7", 1100);
        Locomotives l8 = new Locomotives("L8", 3000);


        List<RailCars> railCarsList = new ArrayList<>();

        for (int i=1; i < 1001; i++) {
            railCarsList.add(new RailCars("R" + i, 5, 100));
        }

    }
}
