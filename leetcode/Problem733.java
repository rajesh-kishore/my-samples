class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        
        // idea is to start filling recursively, image[sr][sc] would contain the old color as base color
      
        
        performFill(image, sr, sc, image[sr][sc], newColor);
        
        return image;
    }
    
    void performFill(int[][] image, int sr, int sc, int oldColor, int newColor ) {
     
           // boundary conditions
           if (sr >= image.length || sr < 0 || sc >= image[0].length || sc < 0 ) {
               return;
           }
        
          if (image[sr][sc] == newColor || image[sr][sc] != oldColor) {// means this is not be filled
               return;
          }
          
          // mark with new color
          image[sr][sc] = newColor;
        
          // start filling in 4 directions
          performFill(image, sr + 1, sc, oldColor, newColor);
          performFill(image, sr - 1, sc, oldColor, newColor);
          performFill(image, sr , sc + 1, oldColor, newColor);
          performFill(image, sr , sc - 1, oldColor, newColor);
    }
    
    
    
}
