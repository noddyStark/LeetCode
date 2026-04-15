package org.leetcode.LinkedList;

public class MergeTwoSortedLists_Recursion {

    private static ListNode mergeLists(ListNode list1, ListNode list2) {

        if (list1 == null) return list2;

        if (list2 == null) return list1;

        if (list1.val <= list2.val) {
            list1.next = mergeLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeLists(list1, list2.next);
            return list2;
        }
    }


    /*
    * TOP
    ---------------------------------------------------
    Call 6: mergeLists(null, 4)
    returns 4
    * returns = 4
    ---------------------------------------------------
    Call 5: mergeLists(4, 4)
    waiting: 4.next = result of Call 6
    * returns = 4 -> 4
    ---------------------------------------------------
    Call 4: mergeLists(4, 3->4)
    waiting: 3.next = result of Call 5
    * returns = 3 -> 4 -> 4
    ---------------------------------------------------
    Call 3: mergeLists(2->4, 3->4)
    waiting: 2.next = result of Call 4
    * returns = 2 -> 3 -> 4 -> 4
    ---------------------------------------------------
    Call 2: mergeLists(1->2->4, 3->4)
    waiting: 1.next = result of Call 3
    * returns = 1 -> 2 -> 3 -> 4 -> 4
    ---------------------------------------------------
    Call 1: mergeLists(1->2->4, 0->3->4)
    waiting: 0.next = result of Call 2
    ---------------------------------------------------
    BOTTOM
        ####   1. Going down the recursion

        mergeLists(1->2->4, 0->3->4)
           ↓
        mergeLists(1->2->4, 3->4)
           ↓
        mergeLists(2->4, 3->4)
           ↓
        mergeLists(4, 3->4)
           ↓
        mergeLists(4, 4)
           ↓
        mergeLists(null, 4)

        mergeLists(null, 4) returns 4

        ####   2. Coming back up

        mergeLists(4, 4)
        first 4.next = 4
        returns 4 -> 4

        mergeLists(4, 3->4)
        3.next = 4 -> 4
        returns 3 -> 4 -> 4

        mergeLists(2->4, 3->4)
        2.next = 3 -> 4 -> 4
        returns 2 -> 3 -> 4 -> 4

        mergeLists(1->2->4, 3->4)
        1.next = 2 -> 3 -> 4 -> 4
        returns 1 -> 2 -> 3 -> 4 -> 4

        mergeLists(1->2->4, 0->3->4)
        0.next = 1 -> 2 -> 3 -> 4 -> 4
        returns 0 -> 1 -> 2 -> 3 -> 4 -> 4

        merge(1,0)
        └── returns 0 + merge(1,3)
            └── returns 1 + merge(2,3)
                └── returns 2 + merge(4,3)
                    └── returns 3 + merge(4,4)
                        └── returns 4 + merge(null,4)
                            └── returns 4

        merge(null,4) = 4
        merge(4,4)    = 4 -> 4
        merge(4,3)    = 3 -> 4 -> 4
        merge(2,3)    = 2 -> 3 -> 4 -> 4
        merge(1,3)    = 1 -> 2 -> 3 -> 4 -> 4
        merge(1,0)    = 0 -> 1 -> 2 -> 3 -> 4 -> 4

    * */

    static void main() {
        ListNode head_1 = new ListNode(1);
        ListNode node_1_2 = new ListNode(2);
        ListNode node_1_4 = new ListNode(4);

        ListNode head_2 = new ListNode(0);
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
