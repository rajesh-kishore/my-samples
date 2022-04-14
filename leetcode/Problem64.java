class Solution {
    public int minPathSum(int[][] grid) {
        
        
        // we will modify the grid itself which would represent min path sum from [i][j] to [m-1][n-1] (bottom right)
        // since we are moving from bottom right to top up , there is no issue in modifying
        
        
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[i].length - 1; j >= 0; j--) {
                
                if (i == grid.length - 1 && j == grid[i].length - 1) {
                    // do nothing 
                } else if (j == grid[i].length -1) {
                    grid[i][j] +=  grid[i+1][j]; // can move only down , modify grid[i][j] with min sum
                } else if (i == grid.length - 1) {
                    grid[i][j] +=  grid[i][j+1]; // can move only right
                } else {
                    grid[i][j] += Math.min(grid[i][j+1] , grid[i+1][j]); // can move either right or down so find out min and add to current
                }
            }
        }
        
        
        return grid[0][0]; // contains minimim sum of all different paths
        
        
    }
}
