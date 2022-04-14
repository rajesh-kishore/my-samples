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
    public ListNode swapPairs(ListNode head) {
        
        
      
        
        ListNode head1 = new ListNode();
        
        // 1 2 3
        
        head1.next = head;
        
        ListNode prev = head1; // dummy
        
        
        while (Objects.nonNull(head)) {
           
           ListNode current = head;        // 3
           ListNode next = current.next; // null
           
           if (Objects.nonNull(next)) { 
                head = next.next;     // 3
                next.next = current; // 2-> 1
                current.next = null; // 2 -> 1 -> null
                prev.next =  next; // dummy -> 2 -> 1 -> 
               
           } else {
                head = next;//null
                prev.next =  current; // dummy -> 2 -> 1 -> 
           }
            
          
           prev =  current; // 1
 
        }
    
        return head1.next;
        
    }
}
