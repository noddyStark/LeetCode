package org.leetcode.QueueUsingArray;

import java.util.Arrays;

public class CircularArrayQueue {

    private int[] array;
    private int size;
    private int front;
    private int rear;

    public CircularArrayQueue(int size) {
        this.size = size;
        this.array = new int[size];
        this.front = -1;
        this.rear = -1;
    }

    public boolean enqueue(int data) {
        if (isFull()) {
            return false;
        }

        if (isEmpty()) {
            front = 0;
        }

        rear = (rear + 1) % size;
        array[rear] = data;
        return true;
    }

    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }

        int result = array[front];

        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % size;
        }

        return result;
    }

    public Integer getFront() {
        if (isEmpty()) {
            return null;
        }
        return array[front];
    }

    public Integer getRear() {
        if (isEmpty()) {
            return null;
        }
        return array[rear];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear + 1) % size == front;
    }

    @Override
    public String toString() {
        return "CircularArrayQueue{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", front=" + front +
                ", rear=" + rear +
                '}';
    }
}