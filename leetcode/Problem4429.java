class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        
        for (int i = obstacleGrid.length - 1; i >= 0; i--) {
            for (int j = obstacleGrid[i].length - 1; j >= 0; j--) {
                
                if (obstacleGrid[i][j] == 1) {
                  obstacleGrid[i][j] = 0;// mark this as no path
                } else if (i == obstacleGrid.length - 1 && j == obstacleGrid[i].length -1) { // at bottom most right
                    obstacleGrid[i][j] = 1;
                } else if (j == obstacleGrid[i].length - 1) {
                    obstacleGrid[i][j] =  obstacleGrid[i+1][j]; // can move only down
                } else if (i == obstacleGrid.length - 1) {
                    obstacleGrid[i][j] =  obstacleGrid[i][j+1]; // can move only right
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i][j+1] + obstacleGrid[i+1][j]; // can move either righ or down
                }
                
                
            }
        }
        
        
        return obstacleGrid[0][0]; // contains sum of all different paths
        
    }
}
