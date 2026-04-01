package org.leetcode.QueueUsingArray;

import java.util.Arrays;

/**
 * FIFO
 *
 * Dequeue <- |_2_|_1_|_4_|_5_|_3_| <- Enqueue
 *             ^
 *           front (always 0)
 *                           ^
 *                          rear
 *
 * Front will always be zero.
 * Rear will move. When there is no element, rear will be -1.
 */
public class ArrayQueue {

    private int[] array;
    private int rear = -1;
    private int size;

    public ArrayQueue(int size) {
        this.size = size;
        this.array = new int[size];
    }

    // O(1)
    public boolean enqueue(int data) {
        if (isFull()) {
            return false;
        }

        rear++;
        array[rear] = data;
        return true;
    }

    // O(n)
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }

        int result = array[0];

        for (int i = 0; i < rear; i++) {
            array[i] = array[i + 1];
        }

        rear--;
        return result;
    }

    // O(1)
    public Integer getFront() {
        if (isEmpty()) {
            return null;
        }
        return array[0];
    }

    public boolean isEmpty() {
        return rear == -1;
    }

    public boolean isFull() {
        return rear == size - 1;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "array=" + Arrays.toString(array) +
                ", rear=" + rear +
                ", size=" + size +
                '}';
    }
}