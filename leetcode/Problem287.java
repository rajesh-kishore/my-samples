class Solution {
    public int findDuplicate(int[] nums) {
     
        for (int i = 0 ; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1]  > 0) {
                nums[Math.abs(nums[i]) - 1] *= -1;
            } else if (nums[Math.abs(nums[i]) - 1] < 0) { // when the same number is already marked as negative then return it
                return Math.abs(nums[i]);
            }
        }
        
       
        
        return -1;
        

    }
}
