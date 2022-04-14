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
    public List<Integer> inorderTraversal(TreeNode root) {
     
        if (Objects.isNull(root)) {
            return Collections.emptyList();
        }
        
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode leftScan = root;
        
        while (Objects.nonNull(leftScan) || !stack.isEmpty()) {
            
            // scan till left most
            while (leftScan != null) {
               stack.push(leftScan);
               leftScan = leftScan.left;
            }
            
            leftScan = stack.pop();
            result.add(leftScan.val);
            leftScan = leftScan.right;
        }
        
        return result;
        
    }
}
