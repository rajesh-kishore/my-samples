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

class Mode {
    int prev = Integer.MIN_VALUE;
    int prevCount = 0;
    int maxCount = Integer.MIN_VALUE;
}

class Solution {
    public int[] findMode(TreeNode root) {
     
       List<Integer> result = new LinkedList<>();
        Mode mode = new Mode();
        findMostOccuredElement(root, mode, result);
        
        int[] primitiveResult = result.stream()
                            .mapToInt(Integer::intValue)
                            .toArray();
        
   
        return primitiveResult;
        
    }
    
    /*
              3
           2      3    
         2           4
       
       
               1
               
                    2  
       
    */
    
    
    private void findMostOccuredElement(TreeNode root, Mode mode, List<Integer> result) {
     
        if (Objects.isNull(root)) {
            return;
        }
        // do inorder traversal , first to the left most
        findMostOccuredElement(root.left, mode, result);
        
        if (mode.prev == root.val) {
            mode.prevCount++;     // increase the count if found same
        } else {
            mode.prevCount = 1; // set to 1
        }
        
        if (mode.prevCount == mode.maxCount) {
                result.add(root.val);
         } else if (mode.prevCount > mode.maxCount) {
                result.clear(); // it means this has greatest of all frequency so clear it and add
                result.add(root.val);
                mode.maxCount = mode.prevCount;
         }
        
         mode.prev = root.val;
        
        
        findMostOccuredElement(root.right, mode, result);
        
    }
    
    
}
