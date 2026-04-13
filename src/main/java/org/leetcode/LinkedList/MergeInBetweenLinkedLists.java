package org.leetcode.LinkedList;


/**
 * 1669. Merge In Between Linked Lists
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.
 * <p>
 * Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 * <p>
 * The blue edges and nodes in the following figure indicate the result:
 * <p>
 * Build the result list and return its head.
 * <p>
 * Example 1:
 * <p>
 * Input: list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * Output: [10,1,13,1000000,1000001,1000002,5]
 * Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place.
 * The blue edges and nodes in the above figure indicate the result.
 * Example 2:
 * <p>
 * Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
 * Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
 * Explanation: The blue edges and nodes in the above figure indicate the result.
 * <p>
 * Constraints:
 * <p>
 * 3 <= list1.length <= 104
 * 1 <= a <= b < list1.length - 1
 * 1 <= list2.length <= 104
 */
public class MergeInBetweenLinkedLists {

    private static ListNode mergedList(ListNode list1, ListNode list2, int a, int b) {
        ListNode prevA = list1;

        //                     A  B
        // List 1 = 10, 1, 13, 6, 9, 5
        // List 2 = 1000000, 1000001, 1000002
        for (int i = 0; i < a - 1; i++) {
            prevA = prevA.next; // prevA = 13
        }

        ListNode afterB = prevA;

        for (int i = 0; i < b - a + 2; i++) {
            afterB = afterB.next; // afterB = 5
        }

        prevA.next = list2; // prevA = 13 -> 1000000

        ListNode tail = list2; // tail = 1000000

        while (tail.next != null) {
            tail = tail.next; // tail = 1000002
        }

        tail.next = afterB; // tail = 1000002 -> 5

        return list1;
    }


    public static void main(String[] args) {

        ListNode head_1 = new ListNode(10);
        ListNode node_1 = new ListNode(1);
        ListNode node_13 = new ListNode(13);
        ListNode node_6 = new ListNode(6);
        ListNode node_9 = new ListNode(9);
        ListNode node_5 = new ListNode(5);
        head_1.next = node_1;
        node_1.next = node_13;
        node_13.next = node_6;
        node_6.next = node_9;
        node_9.next = node_5;

        // list2 = [1000000,1000001,1000002]
        ListNode head_2 = new ListNode(1000000);
        ListNode node_1000001 = new ListNode(1000001);
        ListNode node_1000002 = new ListNode(1000002);
        head_2.next = node_1000001;
        node_1000001.next = node_1000002;

        System.out.print("List 1 = ");
        printList(head_1);
        System.out.println();
        System.out.print("List 2 = ");
        printList(head_2);
        System.out.println();

        ListNode ans = mergedList(head_1, head_2, 3, 4);
        System.out.print("Output List = ");
        printList(ans);
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
    }
}
