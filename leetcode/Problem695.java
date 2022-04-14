class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        
    
        int max = 0;
         for (int i = 0 ; i < grid.length ; i++) {
             for (int j = 0 ; j < grid[i].length ; j++) {
                     int count = markAndCount(grid, i , j);
                     max = max > count ? max : count;
             }
         }
    
         return max;
    }
    
    
    private int markAndCount(int[][] grid, int i, int j) {
        int currentCount = 0;
        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[i].length - 1 || grid[i][j] != 1) {
            return currentCount;
        }
        
        grid[i][j] = -1;
        currentCount++;
        
        currentCount += markAndCount(grid, i + 1 , j);
        currentCount += markAndCount(grid, i - 1 , j);
        currentCount += markAndCount(grid, i , j + 1);
        currentCount += markAndCount(grid, i , j - 1);

        return currentCount;
    }
    
    
    
}
