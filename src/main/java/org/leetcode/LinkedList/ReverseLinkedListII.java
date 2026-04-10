package org.leetcode.LinkedList;


import java.util.LinkedList;
import java.util.Stack;

/**
 * 92. Reverse Linked List II
 * <p>
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * <p>
 * Follow up: Could you do it in one pass?
 *
 */
public class ReverseLinkedListII {

    private static class ListNode {
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

    private static ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 1) Move prev to the node just before 'left'
        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // 2) Start reversing from 'left' to 'right'

        //          prev      curr
        // dummy ->   1   ->   2   ->   3  ->   4  ->    5

        ListNode curr = prev.next;
        ListNode next;

        // 👉 curr does NOT move
        // 👉 Instead, we keep taking curr.next and moving it to the front
        // Take node after curr → move it after prev
        for (int i = 1; i < right - left; i++) {
            next = curr.next;
            curr.next = next.next; // 2 -> 4
            next.next = prev.next; // 3 -> 2
            prev.next = next;      // 1 -> 3
            printList(head);
        }

        return dummy.next;

        /*
         * ##########################
         * ##### STACK SOLUTION #####
         * ##########################
         */
//        Stack<Integer> stack = new Stack<>();
//
//        while (curr != null) {
//
//            if (pointer >= left && pointer <= right) {
//                stack.push(curr.val);
//            }
//            curr = curr.next;
//            pointer++;
//        }
//
//        System.out.println("Stack = " + stack);
//
//        curr = head;
//        pointer = 1;
//
//        while (curr != null) {
//            if (pointer >= left && pointer <= right) {
//                curr.val = stack.pop();
//                System.out.println("Stack after pop = " + stack);
//            }
//            curr = curr.next;
//            pointer++;
//        }

//        return head;
    }

    static void main() {
        ListNode head =
                new ListNode(1, (
                        new ListNode(2, (
                                new ListNode(3, (
                                        new ListNode(4, (
                                                new ListNode(5)))))))));

        System.out.print("Original list: ");
        printList(head);

        int left = 2;
        int right = 4;

        head = reverseBetween(head, left, right);

        System.out.print("Reversed list: ");
        printList(head);
    }

    private static void printList(ListNode head) {
        ListNode curr = head;

        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" -> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }
}
