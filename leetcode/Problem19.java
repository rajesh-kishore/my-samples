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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        

      ListNode firstN = head;
        
      int i = 1;
        
      while (Objects.nonNull(firstN) && i <= n) { // run upto nth node
          firstN = firstN.next;
          i++;
      }
      
       if (Objects.isNull(firstN)) { // if first node itself to be deleted
        head = head.next;
        return head;
      }  
        
      ListNode start = head; 
      ListNode prev = null; // keep this so that we can delete the nth node and point nthnode.next to prev.next;
        
      while (Objects.nonNull(firstN)) {
        firstN = firstN.next;
        prev = start;   
        start = start.next;
      }
        
      prev.next = start.next;
        
     return head;
    }
}
