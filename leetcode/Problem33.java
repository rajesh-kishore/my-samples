class Solution {
    public int search(int[] nums, int target) {
        int indx = findElement(nums, target, 0, nums.length - 1);
        return  indx == -1000000 ? -1 : indx;
    }
    
    /*
         [4,5,6,7,0,1,2], target = 0
         
    
    
    */
    
    int findElement(int[] nums, int target, int low, int high) {
        
        if (low > high) {
            return -1000000;
        }
        
        int mid = (low+high)/2;
        
        if (nums[mid] == target) {
            return mid;
        } 
        
        
        if ( mid -1 >= 0 && nums[low] <= nums[mid -1]) { // if this is sorted
            
            int elem = -1000000;
            if (nums[low] <= target && target <= nums[mid -1]) { // search only when element found in this range
                elem = findElement(nums, target, low, mid - 1);
            }
            
            if (elem != -1000000) {
                return elem;
            } else {
                low = mid + 1; // if not found search in next part mid +1
            }
            
            
            
        } else { // this part must be sorted
            
             int elem = -1000000;
            if (mid + 1 < nums.length && nums[mid+1] <= target && target <= nums[high]) { // // search only when element found in this range
                return findElement(nums, target, mid+1, high);
            }
            
             if (elem != -1000000) {
                return elem;
            } else {
                high = mid - 1; // if not found search in next part mid -1 
            }
        
        }
        
        return findElement(nums, target, low, high); // if not found earlier, then search in this limit
        
    }
    
    
}
