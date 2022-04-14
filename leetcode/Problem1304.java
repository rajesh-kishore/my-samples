class Solution {
    public int[] sumZero(int n) {
        
        
       int res[] = new int[n];
        
       int j = 0;
        
        // n = 5
        
       for (int i = 1 ; i <= n/2 ; i++) {
           res[j++] = i; 
           res[j++] = -i; 
       }
        
        if (n%2 != 0) {
            res[j++] = 0;
        }
        
        
        return res;
        
        
        
    }
}
