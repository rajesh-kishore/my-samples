class Solution {
    public int findMinArrowShots(int[][] points) {
        
       Arrays.sort(points, (p,q) -> Integer.compare(p[1],  q[1]));   

        
        
        int count = 1;
        
        int[] prevInterval = points[0];
        for (int i = 1; i < points.length; i++) {
           if (isOverlappingInterval(prevInterval, points[i])) {
               continue;
           } else {
               count++;
               prevInterval = points[i];
           }
        }
        
        
        
        return count;
    }
    
    
    private boolean isOverlappingInterval(int[] prevInterval, int[] currentInterval) {
        // if end index is overlapping       or            previous end index is greater than current start
        return prevInterval[1] >= currentInterval[1] || prevInterval[1] >= currentInterval[0];
    }
}
