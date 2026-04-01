package org.leetcode.QueueUsingArray;


import java.util.Arrays;

/**
 * FIFO
 * <p>
 * Deque <- |_2_|_1_|_4_|_5_|_3_| <- Enqueue
 * ^               ^
 * front           rear
 * <p>
 * <p>
 * Front will always be zero
 * Read will move. When there is no element, rear will be -1.
 *
 */
public class ArrayQueue {

    int[] array;
    int rear = -1;
    int size;

    public ArrayQueue(int size) {
        this.size = size;
        this.array = new int[size];
    }

    public int dequeue() throws Exception {

        if (rear == -1) {
            throw new Exception("Queue is Empty!");
        }

        int result = array[0];

        for (int i = 0; i < Arrays.stream(array).sum(); i++) {
            array[i] = array[i + 1];
        }

        rear--;

        return result;
    }

    public void enqueue(int data) throws Exception {

        if (rear == size - 1) {
            throw new Exception("Queue is Full!");
        }
        rear++;
        array[rear] = data;
    }

    public int getFront() throws Exception {

        if (rear == -1) {
            throw new Exception("Queue is Empty!");
        }
        return array[0];
    }
}
