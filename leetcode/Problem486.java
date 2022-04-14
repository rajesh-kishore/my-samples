class Solution {
    
    public boolean PredictTheWinner(int[] nums) { 
        
        return isPlayer1TheWinner(nums, 0, nums.length - 1, true, 0, 0);
    }
    
    // just checks if Player1 is the winner
    private boolean isPlayer1TheWinner(int[] nums, int start, int end, boolean isPlayer1Turn, int player1Score, int player2Score) {
        
       if (end < start) {
           return player1Score >= player2Score; 
       }
    
        if (isPlayer1Turn) {
            
            if ( isPlayer1TheWinner(nums, start + 1, end, !isPlayer1Turn, player1Score + nums[start], player2Score) ||
                 isPlayer1TheWinner(nums, start, end - 1, !isPlayer1Turn, player1Score  + nums[end], player2Score)) {
                return true;
            }
            
            return false;
        } else {
             // here we need to check if PLayer 2 wins 
            if ( !isPlayer1TheWinner(nums, start + 1, end, !isPlayer1Turn, player1Score, player2Score + nums[start]) ||
                 !isPlayer1TheWinner(nums, start, end - 1, !isPlayer1Turn, player1Score, player2Score + nums[end])) {
                return false; // to return false as this function is suppose to return true only when Player1 wins
            }
            
             return true; // means Player1 won
        }
        
      
    
    }
    
    
}
