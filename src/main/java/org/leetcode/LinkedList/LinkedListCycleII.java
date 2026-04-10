package org.leetcode.LinkedList;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 142. Linked List Cycle II
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * <p>
 * Do not modify the linked list.
 *
 */
public class LinkedListCycleII {

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

    private static ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        HashMap<ListNode, Integer> map = new HashMap<>();

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                ListNode ptr1 = head;
                ListNode ptr2 = slow;

                while (ptr1 != ptr2) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }

                return ptr1;
            }
        }

        return null;
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

        ListNode ans = detectCycle(head);

        printList(ans);
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
