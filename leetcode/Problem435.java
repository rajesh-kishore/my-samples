

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        
        Arrays.sort(intervals, (p,q) -> p[1] - q[1]);
        
        
        int count = 0;
        int currentSelectedEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (currentSelectedEnd > intervals[i][0]) {
                count++;
            } else {
                currentSelectedEnd = intervals[i][1];
            }
        }    
        
        return count;
        
    }
}
