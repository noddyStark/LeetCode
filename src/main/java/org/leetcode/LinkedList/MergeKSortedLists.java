package org.leetcode.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 23. Merge k Sorted Lists
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted linked list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 * <p>
 * Input: lists = []
 * Output: []
 * Example 3:
 * <p>
 * Input: lists = [[]]
 * Output: []
 */
public class MergeKSortedLists {

    private static ListNode mergeLists(ListNode[] lists) {

        //       0         1         2
        // [  [1,4,5],  [1,3,4],   [2,6]  ]
        //       ^
        //      curr

        ArrayList<Integer> mergedList = new ArrayList<>();

        for (int i = 0; i < lists.length; i++) {
            ListNode curr = lists[i];

            while (curr != null) {
                mergedList.add(curr.val);
                curr = curr.next;
            }
        }

        System.out.println(mergedList);

        Collections.sort(mergedList);

        ListNode head = null;
        ListNode temp = null;

        for (int i = 0; i < mergedList.size(); i++) {

            if (i == 0) {
                head = new ListNode(mergedList.get(i));
                temp = head;
            } else {
                temp.next = new ListNode(mergedList.get(i));
                temp = temp.next;
            }
        }

        return head;
    }

    static void main() {
        ListNode head_1 = new ListNode(1);
        ListNode node_1_2 = new ListNode(4);
        ListNode node_1_4 = new ListNode(5);

        ListNode head_2 = new ListNode(1);
        ListNode node_2_3 = new ListNode(3);
        ListNode node_2_4 = new ListNode(4);

        ListNode head_3 = new ListNode(2);
        ListNode node_3_6 = new ListNode(6);

        head_1.next = node_1_2;
        node_1_2.next = node_1_4;

        head_2.next = node_2_3;
        node_2_3.next = node_2_4;

        head_3.next = node_3_6;

        System.out.print("List1: ");
        printList(head_1);
        System.out.print("List2: ");
        printList(head_2);
        System.out.print("List3: ");
        printList(head_3);

        ListNode[] list = Arrays.asList(head_1, head_2, head_3).toArray(new ListNode[0]);
        ListNode mergedList = mergeLists(list);

        System.out.print("Merged List = ");
        printList(mergedList);
    }

    public static void printList(ListNode head) {
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
