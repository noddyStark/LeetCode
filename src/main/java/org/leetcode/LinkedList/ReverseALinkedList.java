package org.leetcode.LinkedList;


/**
 * LeetCode 206 Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * */
public class ReverseALinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static ListNode reverseList(ListNode head) {

        // Handle null
        if (head == null || head.next == null) {
            return head;
        }

        //                       next
        //                curr
        //         prev
        // null <-   1     2  ->  3  ->   4   ->   5

        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next; // next = 2
            curr.next = prev; // curr -> null
            prev = curr;  // prev = 1
            curr = next; // curr = 2
            printList(prev);
        }

//        Stack<Integer> stack = new Stack<>();
//        ListNode temp = head;
//
//        // Push all values into stack
//        while (temp != null) {
//            stack.push(temp.val);
//            temp = temp.next;
//        }
//
//        System.out.println("Stack after pushing all elements = " + stack);
//
//        // Reassign values from stack back into list
//        temp = head;
//        while (temp != null) {
//            temp.val = stack.pop();
//            temp = temp.next;
//        }

        return prev;
    }

    private static void printList(ListNode head) {
        ListNode temp = head;

        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    static void main() {
        ListNode head =
                new ListNode(1,
                        new ListNode(2,
                                new ListNode(3,
                                        new ListNode(4,
                                                new ListNode(5)))));

        System.out.print("Original list: ");
        printList(head);

        head = reverseList(head);

        System.out.print("Reversed list: ");
        printList(head);
    }
}