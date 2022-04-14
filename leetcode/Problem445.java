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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        l1 = reverseList(l1, null);
        l2 = reverseList(l2, null);
        
        
         ListNode result = new ListNode(0);
        
        ListNode resultHead = result;

        
        int carry = 0;
        
        while ( l1 != null && l2 != null) {
            
            int res = l1.val + l2.val + carry;
            
            ListNode temp = new ListNode(res%10);
            result.next = temp;
            
            result = temp;
            
            carry = res/10;
            
            l1 = l1.next;
            
            l2 = l2.next;
            
        }
        
        while (l1 != null) {
            
            int res = l1.val + carry;
            
            ListNode temp = new ListNode(res%10);
            result.next = temp;
            
            result = temp;

            carry = res/10;
            
            l1 = l1.next;
        }
        
        
        while (l2 != null) {
            
            int res = l2.val + carry;
            
            ListNode temp = new ListNode(res%10);
            result.next = temp;
            
            result = temp;

            carry = res/10;
            
            l2 = l2.next;
        }
        
        if (carry > 0) {
            
           ListNode temp = new ListNode(carry);
           result.next = temp;
        }

        resultHead = resultHead.next;
        
        
        return reverseList(resultHead, null);
        
    }
    
    
     private ListNode reverseList(ListNode current, ListNode prev) {
        
       if (current != null) {
           ListNode tmep = current.next;
           current.next = prev;
           prev = current;
           return reverseList(tmep, prev);
       }
        return prev;
    }
}
