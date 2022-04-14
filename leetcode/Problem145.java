/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 
 
 recursion code is
 
  
 
 void postorder(root) {
     
       if (root is null) {
          return;
        }
     
        postorder(root.left);
     
        postorder(root.right);
     
        process the (root);
     
     }
 
 
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        
        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        
        stack1.push(root);
        /*
                    1
                 2       3   
               4   5       6
               
              add 1 
        
        
        
        */
        
        while (!stack1.isEmpty()) {
            
                TreeNode node = stack1.pop();
             
               
                
                if (Objects.nonNull(node.left)) {
                    stack1.push(node.left);
                }
            
                if (Objects.nonNull(node.right)) {
                    stack1.push(node.right);
                } 
                
                stack2.push(node);
    
        }
        
        
        List<Integer> result = new LinkedList<>();
        
         while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
         }   
        
        
        return result;
        
    }
}
