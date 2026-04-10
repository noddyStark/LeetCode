package org.leetcode.LinkedList;

/**
 * 141. Linked List Cycle
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * <p>
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 */
public class LinkedListCycle {

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

    private static boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }


    static void main() {
        ListNode head =
                new ListNode(3, (
                        new ListNode(2, (
                                new ListNode(0, (
                                        new ListNode(-4, (
                                                new ListNode(2)))))))));

        System.out.print("Original list: ");
        printList(head);

        boolean ans = hasCycle(head);

        System.out.print("Original list has cycle = " + ans);
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
