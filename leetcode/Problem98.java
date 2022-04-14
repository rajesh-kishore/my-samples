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
    public boolean isValidBST(TreeNode root) {
        
        return isValid(root, new AtomicReference<>(null));
    }
    
    
    private boolean isValid(TreeNode root, AtomicReference<Integer> prevRefInt) {
        
        /*
          The idea is to scan to left most and then compare with previous
        
        */
        
        if (Objects.isNull(root)) {
           return true;   
        }
        
        if (!isValid(root.left, prevRefInt)) {
            return false;
        }
        
        //  if prev is greater than equal to cuurent val , then it means its not BST
        if (prevRefInt.get() != null && root.val <=  prevRefInt.get()) {
            return false;
        }
        
        prevRefInt.set(root.val); // set the current val
        
        
        if (!isValid(root.right, prevRefInt)) {
            return false;
        }
        
        
        return true;
        
    }
}
