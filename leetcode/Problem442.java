class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        
        final List<Integer> result = new LinkedList<>();
        
        for (int i = 0 ; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1]  > 0) {
                nums[Math.abs(nums[i]) - 1] *= -1;
            } else if (nums[Math.abs(nums[i]) - 1] < 0) { // when the same number is already marked as negative then capture it
                result.add(Math.abs(nums[i]));
            }
        }
        
       
        
        return result;
        
        
    }
}
