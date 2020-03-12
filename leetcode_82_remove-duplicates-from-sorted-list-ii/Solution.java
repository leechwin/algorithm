/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = new ListNode(0);
        temp.next = head;

        ListNode current = temp;

        while(current.next != null && current.next.next != null) {
            if (current.next.val == current.next.next.val) {
                int duplicateVal = current.next.val;
                while(current.next != null && current.next.val == duplicateVal) {
                    current.next = current.next.next;
                }
            } else {
                current = current.next;
            }
        }
        return temp.next;
    }
}
