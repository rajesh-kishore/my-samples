class Solution {
    public int uniquePaths(int m, int n) {
        
        int moves[][] = new int[m][n]; // would represent total moves from [i][j] to [m-1][n-1] (bottom right)
        
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                
                if (i == m - 1 && j == n -1) { // at bottom most right
                    moves[i][j] = 1;
                } else if (j == n-1) {
                    moves[i][j] =  moves[i+1][j]; // can move only down
                } else if (i == m-1) {
                    moves[i][j] =  moves[i][j+1]; // can move only right
                } else {
                    moves[i][j] = moves[i][j+1] + moves[i+1][j]; // can move either righ or down
                }
                
                
            }
        }
        
        
        return moves[0][0]; // contains sum of all different paths
        
    }
}
