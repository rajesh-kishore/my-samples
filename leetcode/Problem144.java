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
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
     
        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        
        List<Integer> nodes = new LinkedList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        
        stack.push(root); 
        
        while (!stack.isEmpty()) {
           
           TreeNode node = stack.pop();
           nodes.add(node.val);
           
           if (Objects.nonNull(node.right)) {
                stack.push(node.right); 
           }
            
           if (Objects.nonNull(node.left)) {
                stack.push(node.left); 
           }
            
        }
        return nodes;
        
        
    }
}
