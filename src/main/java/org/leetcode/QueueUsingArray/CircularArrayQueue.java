package org.leetcode.QueueUsingArray;

public class CircularArrayQueue {

    int[] array;
    int size;
    int front, rear = 1;

    public CircularArrayQueue(int size) {
        this.size = size;
        array = new int[size];
    }

    // O(1) Time
    public void enqueue(int data) throws Exception {
        if ((rear + 1) % size == front) {
            throw new Exception("Queue is Full");
        }

        if (front == -1) {
            front = 0;
        }

        rear = (rear + 1) % size;

        array[rear] = data;
    }

    // O(1) Time
    public int dequeue() throws Exception {
        if (front == -1) {
            throw new Exception("Queue is Empty!");
        }

        int result = array[0];

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % size;
        }

        return result;
    }

    public boolean isEmpty() {
        if (front == -1) {
            return true;
        }
        return false;
    }
}
