package org.leetcode.LinkedList;

/**
 * LeetCode 206 Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 */
public class ReverseALinkedList_Recursive {

    private static ListNode reverse(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        /**
         * TOP (unwinding phase — actual reversal happens here)
         * ---------------------------------------------------
         * Call 3: reverse(6), head = 6
         * Base case hit since 6.next = null
         * return 6 (this becomes the new head)
         * ---------------------------------------------------
         * Call 2: reverse(4), head = 4, 4.next = 6
         * waiting: last = result of call 3 → last = 6
         *
         * Reverse the link:
         * 4.next.next = 4  →  6.next = 4
         *
         * Break old forward link:
         * 4.next = null
         *
         * Current list: 6 → 4 → null
         * return last = 6
         * ---------------------------------------------------
         * Call 1: reverse(1), head = 1, 1.next = 4
         * waiting: last = result of call 2 → last = 6
         *
         * Reverse the link:
         * 1.next.next = 1  →  4.next = 1
         *
         * Break old forward link:
         * 1.next = null
         *
         * Current list: 6 → 4 → 1 → null
         * return last = 6
         * ---------------------------------------------------
         *
         * Final Answer: 6 → 4 → 1 → null
         */
        ListNode last = reverse(head.next);
        System.out.print("Last = ");
        printList(last);
        System.out.print("Head = ");
        printList(head);

        head.next.next = head;
        head.next = null;
        System.out.print("After Update Last = ");
        printList(last);
        System.out.print("After Update Head = ");
        printList(head);

        return last;
    }

    static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node4 = new ListNode(4);
        ListNode node6 = new ListNode(6);

        head.next = node4;
        node4.next = node6;

        System.out.print("Original List = ");
        printList(head);
//        System.out.print("Reversed List = ");
        ListNode reversed = reverse(head);
        printList(reversed);
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
}
