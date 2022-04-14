class Solution {
    public int closedIsland(int[][] grid) {
        
      
        // first discard all the edges having 0 as they do not qualify for closed island
        // additionaly also discard the indices neighbour to edges with 0 recurcisvely as they also          // cant be considered closed
        
        for (int i = 0 ; i < grid.length ; i++) {
            for (int j = 0 ; j < grid[i].length ; j++) {
                
                if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[i].length - 1) {
                    discard(grid, i, j);
                }
            }    
            
        }
        // till here , all the edges with 0 and its neighbour marked with 0s are discarded
        
        // start counting the closed island now
        
        int closedIslandCount = 0;
         for (int i = 1 ; i < grid.length - 1 ; i++) {
            for (int j = 1 ; j < grid[i].length - 1; j++) {
                
                if (grid[i][j] == 0) { // only the leftover would be marked with 0
                    discard(grid, i, j); // start from i, j and scan till you continue getting 0
                    closedIslandCount++; // after scanning all connected , there is one closed island
                }
            }    
            
        }
        
        return closedIslandCount;
    }
    
    private void discard(int[][] grid, int i, int j) {
        
         if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] != 0) {
               return;     
         }
        
        grid[i][j] = -1; // mark it , so that it does nt come in next scan
        
        discard(grid, i + 1, j);
        discard(grid, i - 1, j);
        discard(grid, i, j + 1);
        discard(grid, i, j - 1);
        
    }
}
