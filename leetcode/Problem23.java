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
    
    public ListNode mergeKLists(ListNode[] lists) {
     
        ListNode head = new ListNode();
        
        ListNode head1 = head;
        
        PriorityQueue<ListNode> queue = new PriorityQueue<>( (p,q) ->  Integer.valueOf(p.val).compareTo(Integer.valueOf(q.val)));
        
        for (int i = 0 ; i < lists.length ; i++) {
            
            if (Objects.nonNull(lists[i])) {
               queue.add(lists[i]); 
            }
        }
        
        
        
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            head.next = node;
         
            if (Objects.nonNull(node.next)) {
                queue.add(node.next);
            }
            head = head.next;
            
        }
        
        
        
        return head1.next;
    }
}
