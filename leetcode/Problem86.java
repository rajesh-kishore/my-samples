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
    public ListNode partition(ListNode head, int x) {
        
       
        // maintain two head lists so that we capture before & after in seperate list
        ListNode beforeListHead = null;
        ListNode afterListHead = null;
        
        ListNode beforeListPrev = null; // also maintain prev of before & after list, so that we keep adding to prev
        ListNode afterListPrev = null;
       
        /*
         [1,4,3,2,5,2]
         3
        
        
        */
        
         while (Objects.nonNull(head)) {
             
             if (head.val < x ) {
                 if (Objects.isNull(beforeListHead)) {
                     beforeListHead = head; // when its first before head, maintain beforeListHead
                     beforeListPrev = head;
                 } else {
                     beforeListPrev.next = head; // add to previous
                     beforeListPrev = head;
                 }
                 head = head.next;
                 beforeListPrev.next = null;
             } else {
                 
                  if (Objects.isNull(afterListHead)) {
                     afterListHead = head; // when its first after head, maintain afterListHead
                     afterListPrev = head;
                 } else {
                     afterListPrev.next = head; // add to previous
                     afterListPrev = head;
                     
                 }
                  head = head.next; // advance pointer to next
                  afterListPrev.next = null; 
             }
            
         }
        
        if (!Objects.isNull(beforeListHead)) { // merge everything to after so that we retirn just one lsit
            beforeListPrev.next = afterListHead;
        } else {
            beforeListHead = afterListHead;
        }
        
        
        return beforeListHead;
    }   
        
}

