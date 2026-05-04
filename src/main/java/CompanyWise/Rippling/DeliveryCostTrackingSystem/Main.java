package CompanyWise.Rippling.DeliveryCostTrackingSystem;

public class Main {

    public static void main(String[] args) {

        DeliveryCostTrackingSystem system = new DeliveryCostTrackingSystem();

        // Add Drivers
        system.addDriver(1);
        system.addDriver(2);

        // Add Delivery
        system.addDelivery(1, 0, 10);
        system.addDelivery(1, 20, 40);
        system.addDelivery(2, 30, 50);

        system.payUpToTime(45);

        System.out.println("Total Cost  = " + system.getTotalCost());
        System.out.println("get_cost_to_be_paid() : " + system.getCostToBePaid());
        System.out.println("Active Drivers = " + system.getMaxActiveDriversInLast24Hours(50));
    }
}