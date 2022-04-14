/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        
        ListNode newHead = new ListNode();// create a dummy head
        newHead.next = head;
       
        int i = 1;
        ListNode prev = newHead;  // find the first element till left
        while (Objects.nonNull(head) && i < left) {
            prev = head;
            head = head.next;
            i++;
        }
        
        ListNode prevRetained = prev; // retain before one ,so that its next can be set later
        
        prev = null;
        
        ListNode head1 = head;
        
        while (Objects.nonNull(head) && i <= right) { // reverse the list till right
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            i++;
            head = temp;
        }
        
        prevRetained.next = prev;     // prev represnets right end , hence setting it to previous before
        head1.next = head; // head represents current pointer and head1 represnts left pointer , so need to set left.next to right next
        
        newHead = newHead.next;
        
        return newHead;
        
    }
}
