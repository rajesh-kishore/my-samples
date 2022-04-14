class Solution {
    
    
    public int rob(int[] nums) {
    
    
        Integer[] maxAmtTillNthHouse = new Integer[nums.length]; // each index value would represent max value till that point
   
        return findMaxAmtTillNthHouse(nums, 0, maxAmtTillNthHouse);
    }
    
    
    private int findMaxAmtTillNthHouse(int[] nums,int currentHouseNo, Integer[] maxAmtTillNthHouse) {
    
        if (currentHouseNo >= nums.length) {
            return 0;
        }
        
        if (Objects.nonNull(maxAmtTillNthHouse[currentHouseNo])) { // if already calculated
            return maxAmtTillNthHouse[currentHouseNo]; 
        }
        
        
        
        // whatever is max out of current + 2 or immediate house
        maxAmtTillNthHouse[currentHouseNo] = 
            Math.max(nums[currentHouseNo] + findMaxAmtTillNthHouse(nums, currentHouseNo + 2, maxAmtTillNthHouse),
                    findMaxAmtTillNthHouse(nums, currentHouseNo + 1, maxAmtTillNthHouse));
            
        
        return maxAmtTillNthHouse[currentHouseNo];
    }
    
    
}
