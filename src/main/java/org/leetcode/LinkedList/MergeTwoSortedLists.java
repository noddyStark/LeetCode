package org.leetcode.LinkedList;

/**
 * 21. Merge Two Sorted Lists
 * You are given the heads of two sorted linked lists list1 and list2.
 * <p>
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Return the head of the merged linked list.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 * <p>
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 * <p>
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedLists {

    private static ListNode mergeLists(ListNode list1, ListNode list2) {

        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode head;

        if (list1.val <= list2.val) {
            head = list1;
            list1 = list1.next;
        } else {
            head = list2;
            list2 = list2.next;
        }

        ListNode current = head;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return head;
    }

    static void main() {
        ListNode head_1 = new ListNode(1);
        ListNode node_1_2 = new ListNode(2);
        ListNode node_1_4 = new ListNode(4);

        ListNode head_2 = new ListNode(1);
        ListNode node_2_3 = new ListNode(3);
        ListNode node_2_4 = new ListNode(4);

        head_1.next = node_1_2;
        node_1_2.next = node_1_4;

        head_2.next = node_2_3;
        node_2_3.next = node_2_4;


        System.out.print("List1: ");
        printList(head_1);
        System.out.print("List2: ");
        printList(head_2);

        ListNode mergedList = mergeLists(head_1, head_2);

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
