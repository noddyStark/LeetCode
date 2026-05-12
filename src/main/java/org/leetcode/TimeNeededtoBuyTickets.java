package org.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class TimeNeededtoBuyTickets {

    static class Pair {
        int tickets;
        int index;

        public Pair(int tickets, int index) {
            this.tickets = tickets;
            this.index = index;
        }

        static void main() {

            int[] tickets = {2, 3, 2};
            int k = 2;

            int result = timeRequiredToBuy(tickets, k);

            System.out.println("result = " + result);
        }

        private static int timeRequiredToBuy(int[] tickets, int k) {

            if (k > tickets.length) {
                return 0;
            }

            Deque<Pair> queue = new ArrayDeque<>();
            /**
             numOfTickets = [1, 3, 2]

             queue = [(2,0) (3 ,1) (2,2)] => 0 sec
             queue = [(3,1) (2 ,2) (1,0)] => 1 sec
             queue = [(2,2) (1 ,0) (2,1)] => 2 sec
             queue = [(1,0) (2 ,1) (1,2)] => 3 sec
             queue = [(2 ,1) (1,2)]       => 4 sec
             queue = [(1 ,2) (1,1)]       => 5 sec
             queue = [(1,1)]              => 6 sec
             */

            int numOfTickets = tickets[k];

            for (int i = 0; i < tickets.length; i++) {
                queue.offerLast(new Pair(tickets[i], i));
            }

            int time = 0;

            while (numOfTickets != 0 && !queue.isEmpty()) {
                System.out.println("Queue = " + queue + " at time = " + time);

                Pair pair = queue.poll();

                int ticketCount = pair.tickets;
                ticketCount = ticketCount - 1;
                int index = pair.index;

                if (index == k & numOfTickets == 0) {
                    return time;
                }

                if (ticketCount != 0) {
                    queue.offerLast(new Pair(ticketCount, index));
                }

                if (index == k) {
                    numOfTickets--;
                    System.out.println("Updating numOfTickets since index is 2 = " + numOfTickets);
                }

                time++;
            }

            return time;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "tickets=" + tickets +
                    ", index=" + index +
                    '}';
        }
    }
}
