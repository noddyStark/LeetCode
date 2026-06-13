package CompanyWise.DoorDash;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/*
Codecraft round was not a leetcode style problem, but rather an exercise you do collaboratively with the interviewer. I found the discussion pretty good and in part, the interviewer was actually present and interested in hearing my thought process!

Problem
Overview
As part of migrating away from a monolith to a micro-service architecture your team has been tasked with building out a new Payments Service. Your project is to build out the Dasher payout logic. As part of your work you will need to call an upstream dependency to retrieve the relevant data.

Goals
Create an API with the following endpoint
POST payout
Input: dasherId
Output: dollar amount the dasher gets paid
This endpoint needs to request delivery information from a separate endpoint
Requirements
The first version of the payment model is naively based on how much time the Dasher spends fulfilling each order. Given the sequence of order activities from a given Dasher on a given day, calculate the Dasher pay based on the following payment rules:
base pay rate: $0.3 per minute
multi order pay rate: # ongoing deliveries * base pay rate
test coverage
Calculate how much to pay out for the Dasher

Service needs to contain logic how much to pay for dashboard according to external data from another service. For the purpose of this exercise, you can mock the data and create a function to return the set of data

Gotchas
The interviewer asked me to come up with the mock data, but my representation matched what the interviewer had in mind, so the example data was provided.
There were no followup parts/features to this question! I was able to get a working implementation working so we spent time on edge cases and basic systems thinking
Followups
Any edge cases you can think of in your implementation? Correct for those after discussing with the interviewer and test your code. Use your critical thinking here
What would happen if the upstream service had high latency? What would you do?
* */
enum ActivityType {
    PICKUP,
    DROPOFF
}

class DeliveryActivity {
    String orderId;
    LocalDateTime timestamp;
    ActivityType activityType;

    public DeliveryActivity(String orderId, LocalDateTime timestamp, ActivityType type) {
        this.orderId = orderId;
        this.timestamp = timestamp;
        this.activityType = type;
    }
}

interface DeliveryActivityClient {
    List<DeliveryActivity> getDeliveryActivities(String dasherId);
}

class MockDeliveryActivityClient implements DeliveryActivityClient {

    @Override
    public List<DeliveryActivity> getDeliveryActivities(String dasherId) {

        LocalDateTime base = LocalDateTime.of(2026, 6, 10, 10, 0);

        return List.of(
                // Order 1
                new DeliveryActivity("order_1", base.plusMinutes(0), ActivityType.PICKUP),
                new DeliveryActivity("order_1", base.plusMinutes(20), ActivityType.DROPOFF),

                // Order 2
                new DeliveryActivity("order_2", base.plusMinutes(10), ActivityType.PICKUP),
                new DeliveryActivity("order_2", base.plusMinutes(30), ActivityType.DROPOFF)
        );
    }
}

class PayOutService {

    private Double BASE_RATE_PER_MINUTE = 0.3;

    private final DeliveryActivityClient DeliveryActivityClient;

    public PayOutService(DeliveryActivityClient DeliveryActivityClient) {
        this.DeliveryActivityClient = DeliveryActivityClient;
    }

    public double calculatePayout(String dasherId) {

        List<DeliveryActivity> deliveryActivities = new ArrayList<>(
                DeliveryActivityClient.getDeliveryActivities(dasherId)
        );

        deliveryActivities.sort(Comparator.comparing(activity -> activity.timestamp));

        int activeDeliveries = 0;
        double totalPay = 0.0;

        LocalDateTime previousTimestamp = deliveryActivities.getFirst().timestamp;

        Set<String> activeOrders = new HashSet<>();

        for (DeliveryActivity deliveryActivity : deliveryActivities) {

            long minutes = Duration.between(previousTimestamp, deliveryActivity.timestamp).toMinutes();

            if (minutes > 0 && activeDeliveries > 0) {
                totalPay += minutes * activeDeliveries * BASE_RATE_PER_MINUTE;
            }

            if (deliveryActivity.activityType.equals(ActivityType.PICKUP)) {
                activeOrders.add(deliveryActivity.orderId);
                activeDeliveries++;
            } else {
                activeDeliveries--;
                activeOrders.remove(deliveryActivity.orderId);
            }

            previousTimestamp = deliveryActivity.timestamp;
        }

        return totalPay;
    }

    public static class Main {
        public static void main(String[] args) {
            DeliveryActivityClient activityClient = new MockDeliveryActivityClient();
            PayOutService payOutService = new PayOutService(activityClient);

            double amount = payOutService.calculatePayout("dasher1");

            System.out.println("Payout = " + amount + "$");
        }
}
}
