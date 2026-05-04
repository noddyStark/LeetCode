package CompanyWise.Rippling;

public class Delivery {

    int driverId;
    int startTime;
    int endTime;
    int cost;
    boolean isPaid;

    @Override
    public String toString() {
        return "Delivery{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", cost=" + cost +
                ", isPaid=" + isPaid +
                '}';
    }

    public Delivery(int driverId, int startTime, int endTime, int cost) {
        this.driverId = driverId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.isPaid = false;
    }

    public int getCost() {
        return cost;
    }

}
