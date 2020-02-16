/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode tempCurrNext = curr.next;
            curr.next = prev;
            prev = curr;
            // next node
            curr = tempCurrNext;
        }
        return prev;
    }
}
