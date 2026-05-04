package CompanyWise.Rippling.DeliveryCostTrackingSystem;

import java.util.*;

/*
You need to implement a delivery cost tracking system for a logistics platform.
The system tracks
1. driver deliveries,
2. costs, and
3. analytics based on delivery time windows.

The implementation will be done in three parts, each building on the previous one.

Part 1: Cost Calculation
Operations
1. add_driver(driverId) :
   - Registers a new driver to the system.
   - Each driver is uniquely identified by a string/integer driverId.

2. add_delivery(driverId, startTime, endTime)
    - Adds a delivery for a driver with given start and end time.
    - Each delivery has a cost equal to the duration in minutes.
    - A driver can have multiple deliveries.
    - Times are integers in minutes (Unix time or relative timestamp).

3. get_total_cost()
    - Returns the total cost across all drivers and deliveries added so far.

Part 2: Payment Tracking
Extend your implementation to support partial payments.

Additional Operations
4. pay_up_to_time(upToTime)
    - Marks all deliveries that ended before or at upToTime as paid.
    - Paid deliveries are excluded from future cost calculations.

5. get_cost_to_be_paid()
    - Returns the total cost of unpaid deliveries only.

Part 3: Analytics
Add analytics support for monitoring delivery load.

Additional Operation
6. get_max_active_drivers_in_last_24_hours(currentTime)
    - Given a currentTime, return the maximum number of unique drivers that were active in any 24-hour window ending at currentTime.
    - A driver is considered active in the window if they had any delivery that overlaps the 24-hour period [currentTime - 1440, currentTime].
Constraints
- All timestamps are integers in minutes.
- Delivery times are well-formed: startTime <= endTime.
- There can be up to 10⁵ drivers and 10⁶ deliveries.
- All functions should aim for O(log n) or O(1) where possible.

Example
add_driver("D1");
add_driver("D2");

add_delivery("D1", 0, 10);  // +10 cost
add_delivery("D1", 20, 40); // +20 cost
add_delivery("D2", 30, 50); // +20 cost

get_total_cost();           // Output: 50

pay_up_to_time(45);         // Marks D1's 10-min and 20-min delivery as paid
get_cost_to_be_paid();      // Output: 20 (only D2's delivery is unpaid)

get_max_active_drivers_in_last_24_hours(50); // Output: 2 (D1 and D2 had deliveries in [26, 50])
 * */

public class DeliveryCostTrackingSystem {

    Map<Integer, Driver> mapOfDrivers;
    PriorityQueue<Delivery> unpaidDeliveries =
            new PriorityQueue<>((a, b) -> a.endTime - b.endTime);


    // Deliveries waiting to enter the window
    // Sorted by earliest startTime first
    PriorityQueue<Delivery> byStartTime =
            new PriorityQueue<>(Comparator.comparingInt(d -> d.startTime));

    // Deliveries currently inside the window
    // Sorted by earliest endTime first
    PriorityQueue<Delivery> byEndTime =
            new PriorityQueue<>(Comparator.comparingInt(d -> d.endTime));

    // driverId -> number of active deliveries for that driver
    private final Map<Integer, Integer> activeDeliveryCountByDriver = new HashMap<>();

    int activeDrivers = 0;

    private int unpaidTotalCost = 0;
    private int totalCost = 0;


    public DeliveryCostTrackingSystem() {
        this.mapOfDrivers = new HashMap<>();
    }

    public void addDriver(int driverId) {
        mapOfDrivers.putIfAbsent(driverId, new Driver(driverId));
    }

    public void addDelivery(int driverId, int startTime, int endTime) {

        Driver driver = mapOfDrivers.get(driverId);

        int cost = endTime - startTime;

        Delivery delivery = new Delivery(driverId, startTime, endTime, cost);

        // 1. Store
        driver.getDeliveries().add(delivery);

        // 2. Cost tracking
        totalCost += cost;
        unpaidTotalCost += cost;

        // 3. Payment system
        unpaidDeliveries.offer(delivery);
        /*
        *  unpaidDeliveries :  [
                    Delivery{startTime=0, endTime=10, cost=10, isPaid=false},
                    Delivery{startTime=20, endTime=40, cost=20, isPaid=false},
                    Delivery{startTime=30, endTime=50, cost=20, isPaid=false}
                   ]
        * */

        // 4. Sliding window system
        // We only add to byStartTime here.
        // Later, during query, delivery will move from byStartTime -> byEndTime.
        byStartTime.offer(delivery);
    }

    // Marks all deliveries that ended before or at upToTime as paid.
    // Paid deliveries are excluded from future cost calculations.
    public void payUpToTime(int upToTime) {
        while (!unpaidDeliveries.isEmpty() && unpaidDeliveries.peek().endTime <= upToTime) {

            Delivery delivery = unpaidDeliveries.poll();

            if (!delivery.isPaid) {
                delivery.isPaid = true;
                unpaidTotalCost = unpaidTotalCost - delivery.cost;
            }
        }
    }

    public int getMaxActiveDriversInLast24Hours(int currentTime) {

        int windowStart = currentTime - 1440;

        /*
         * Step 1:
         * Add all deliveries that have started by currentTime.
         *
         * Condition:
         * delivery.startTime <= currentTime
         *
         * These deliveries may overlap the 24-hour window.
         */
        while (!byStartTime.isEmpty()
                && byStartTime.peek().startTime <= currentTime) {

            Delivery delivery = byStartTime.poll();

            // Now this delivery is considered active candidate,
            // so track it by endTime for future expiry.
            byEndTime.offer(delivery);

            int driverId = delivery.driverId;

            int currentCount =
                    activeDeliveryCountByDriver.getOrDefault(driverId, 0);

            // If this driver had no active deliveries before,
            // this is a new active driver.
            if (currentCount == 0) {
                activeDrivers++;
            }

            activeDeliveryCountByDriver.put(driverId, currentCount + 1);
        }

        /*
         * Step 2:
         * Remove deliveries that ended before the 24-hour window starts.
         *
         * Window = [currentTime - 1440, currentTime]
         *
         * If delivery.endTime < windowStart,
         * then it does NOT overlap the window anymore.
         */
        while (!byEndTime.isEmpty()
                && byEndTime.peek().endTime < windowStart) {

            Delivery delivery = byEndTime.poll();

            int driverId = delivery.driverId;

            int currentCount =
                    activeDeliveryCountByDriver.get(driverId);

            // This was the driver's last active delivery
            if (currentCount == 1) {
                activeDeliveryCountByDriver.remove(driverId);
                activeDrivers--;
            } else {
                activeDeliveryCountByDriver.put(driverId, currentCount - 1);
            }
        }

        return activeDrivers;
    }

    public Driver getDriver(int driverId) {
        return mapOfDrivers.get(driverId);
    }

    public Map<Integer, Driver> getMapOfDrivers() {
        return mapOfDrivers;
    }

    public int getCostToBePaid() {
        return unpaidTotalCost;
    }

    public int getTotalCost() {
        return totalCost;
    }
}
