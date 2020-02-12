/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode head = result;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while(p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                result.next = p1;
                p1 = p1.next;
            } else {
                result.next = p2;
                p2 = p2.next;
            }

            result = result.next;
        }

        if (p1 != null) {
            result.next = p1;
        }
        if (p2 != null) {
            result.next = p2;
        }

        // remove first node
        return head.next;
    }
}