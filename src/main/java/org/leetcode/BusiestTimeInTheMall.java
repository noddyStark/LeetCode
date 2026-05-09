package org.leetcode;


import java.util.*;

/*
* The Westfield Mall management is trying to figure out what the busiest moment at the mall was last year.
* You’re given data extracted from the mall’s door detectors. Each data point is represented as an integer array whose size is 3.
* The values at indices 0, 1 and 2 are the timestamp, the count of visitors, and whether the visitors entered or exited the mall (0 for exit and 1 for entrance), respectively.
* Here’s an example of a data point: [ 1440084737, 4, 0 ].
* Note that time is given in a Unix format called Epoch, which is a nonnegative integer holding the number of seconds that
* have elapsed since 00:00:00 UTC, Thursday, 1 January 1970.
*
* Given an array, data, of data points, write a function findBusiestPeriod that returns the time at which the mall reached
* its busiest moment last year. The return value is the timestamp, e.g. 1480640292. Note that if there is more than one
* period with the same visitor peak, return the earliest one.
* Assume that the array data is sorted in an ascending order by the timestamp. Explain your solution and analyze its time
* and space complexities.

Example:

input:  data = [ [1487799425, 14, 1],
                 [1487799425, 4,  0],
                 [1487799425, 2,  0],
                 [1487800378, 10, 1],
                 [1487801478, 18, 0],
                 [1487801478, 18, 1],
                 [1487901013, 1,  0],
                 [1487901211, 7,  1],
                 [1487901211, 7,  0] ]

output: 1487800378 # since the increase in the number of people
                   # in the mall is the highest
* */
public class BusiestTimeInTheMall {

    static class Pair {
        int maxCountOfPeople;
        int timeStamp;

        public Pair(int maxCountOfPeople, int timeStamp) {
            this.maxCountOfPeople = maxCountOfPeople;
            this.timeStamp = timeStamp;
        }
    }

    static void main() {
        int[][] data = {
                {1487799425, 14, 1}, // 14
                {1487799425, 4, 0}, // 10
                {1487799425, 2, 0}, // 8
                {1487800378, 10, 1}, // 18
                {1487801478, 18, 0}, // 0
                {1487801478, 18, 1}, // 18
                {1487901013, 1, 0}, // 17
                {1487901211, 7, 1}, // 24
                {1487901211, 7, 0} // 17
        };

        System.out.println(findBusiestPeriod(data));
    }

//    private static int findBusiestPeriod(int[][] data) {
//
//        // O(nlog(n))
//        TreeMap<Integer, Integer> map = new TreeMap<>();
//        int currentCountOfPeople = 0;
//
//        for (int[] current : data) {
//            int timeStamp = current[0];
//            int people = current[1];
//            int entryOrExit = current[2];
//
//            if (entryOrExit == 1) {
//                currentCountOfPeople = currentCountOfPeople + people;
//            } else {
//                currentCountOfPeople = currentCountOfPeople - people;
//            }
//
//            map.put(timeStamp, currentCountOfPeople);
//
//        }
//
//        System.out.println("map = " + map);
//        // map = {1487800378=18, 1487901211=17, 1487801478=18, 1487901013=17, 1487799425=8}
//
//        int timeStamp = 0;
//        int max = 0;
//
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if (max < entry.getValue()) {
//                max = entry.getValue();
//                timeStamp = entry.getKey();
//            }
//        }
//
//        return timeStamp;
//    }

    private static int findBusiestPeriod(int[][] data) {

        int currentPeople = 0;
        int maxPeople = 0;
        int busiestTime = 0;

        for (int i=0; i<data.length; i++) {
            int timeStamp = data[i][0];
            int people = data[i][0];
            int entryOrExit = data[i][0];

            if (entryOrExit == 1) {
                currentPeople += people;
            } else {
                currentPeople -= people;
            }

            if (i == data.length -1 || data[i+1][0] != timeStamp) {
                if (maxPeople < currentPeople) {
                    maxPeople = currentPeople;
                    busiestTime = timeStamp;
                }
            }
        }

        return busiestTime;
    }
}
