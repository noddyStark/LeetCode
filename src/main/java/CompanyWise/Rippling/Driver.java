package CompanyWise.Rippling;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    private final int driverId;
    private final List<Delivery> deliveries;

    public Driver(int driverId) {
        this.driverId = driverId;
        this.deliveries = new ArrayList<>();
    }

    public int getDriverId() {
        return driverId;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", deliveries=" + deliveries +
                '}';
    }
}
