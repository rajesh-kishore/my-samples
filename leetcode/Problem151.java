class Solution {
    public String reverseWords(String s) {
        
        StringBuilder sb = new StringBuilder();
        
        
        int lastNonWhiteSpace = -1;
        int i = s.length() -1;
        for (; i >= 0; i--) {
            
            if (Character.isWhitespace(s.charAt(i))) {
              
                if (lastNonWhiteSpace != -1) {
                    if (sb.toString().length() != 0) {
                        sb.append(" ");
                    }
                    sb.append(s.substring(i+1, lastNonWhiteSpace + 1));
                }
                
                lastNonWhiteSpace = -1;
                continue;   
            }
            
            if (lastNonWhiteSpace == -1) {
                lastNonWhiteSpace = i;
            }
            
            
        }
        
        if (lastNonWhiteSpace != -1) {
            if (sb.toString().length() != 0) {
                sb.append(" ");
            }
            sb.append(s.substring(i+1, lastNonWhiteSpace + 1));
        }
        
        return sb.toString();

    }
}
