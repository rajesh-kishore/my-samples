class Solution {
    public int firstMissingPositive(int[] nums) {
        /*
        [3,     4,    -1,     1]
                       
        
         step a: place positive number less than nums.length at its correct place (i-1), and ignore non positive number , at the end of traversal nums would contain number at its correct place- the idea is to put positive number nums[i] at its correct place (i-1) starting from 0th index
         
         step b: start from 1 to numss.length and look for val in nums[i-1] if its equal to i, if not return i which is missing positive nummber

        
        
        so ,
        0       1     2       3    index
        [3,     4,    -1,     1]   i = 0
        [-1,     4,    3,     1]  i = 0
        [-1,     4,    3,     1] i = 1
        [-1,     4,    3,     1] i = 2
        [-1,     4,    3,     1] i = 3
        [1,      4,    3,    -1] i = 3
        [1,      4,    3,    -1] i = 4
        
        */
        
        // step a - put nums[i] at its correct index
        
        for (int i = 0; i < nums.length;) {
            // ignore number which is less than equal to 0 and ignore numbers which are greater than lenght as anyway we are intersted only in smallest missing //number
            // also ignore if the number is already at its correct place i - 1
            if (nums[i] <= 0 || nums[i] >= nums.length || nums[i] == nums[nums[i] - 1]) {
                i++;
            } else {
                // swap the numbers
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
                
            }
        }
        
        int i = 1;
        for (; i <= nums.length; i++) {
            if (nums[i-1] != i) {
                return i;
            }
        }
        
        
        return i; 
        
        
    }
}
