package org.leetcode.LinkedList;

import java.util.HashSet;
import java.util.Set;

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
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        head.next = node2;
        node2.next = node0;
        node0.next = node4;

        // Create cycle: tail -> node2 (pos = 1)
        node4.next = node2;

        System.out.print("List: ");
        printList(head);

        boolean ans = hasCycle(head);

        System.out.println("Has cycle = " + ans);
    }


    private static void printList(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode curr = head;

        while (curr != null) {

            // If we see the node again → cycle detected
            if (visited.contains(curr)) {
                System.out.print(curr.val + " (cycle starts here)");
                break;
            }

            System.out.print(curr.val);
            visited.add(curr);

            if (curr.next != null) {
                System.out.print(" -> ");
            }

            curr = curr.next;
        }

        System.out.println();
    }
}
