class Pair {
    char c;
    int frequency;
    
    Pair(char c, int fre) {
        this.c = c;
        this.frequency = fre;
    }
}

class Solution {
    public int minDeletions(String s) {
       
        /* aaabbbcc
        
           aabbbcc
           abbbc
           
           a3, b3, c2
           
        
        */
        
        // count the frequency;
        
        Map<Character, Integer> frequencyMap = new HashMap<>();
        
        for (char c: s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        
        
        Set<Integer> alreadyPresentFrequency = new HashSet<>(); // we should nt have duplicate frequecny
        
        int deletedCharacterCount = 0;
        
        //abc -> a1 b1 c1
        
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
         
               int currentFreq = entry.getValue();
                
                while (currentFreq != 0 && alreadyPresentFrequency.contains(currentFreq)) { //  keep decrementing current character frequecny untill no similar frequency is found and keep incrementing
                 
                    currentFreq--;
                    deletedCharacterCount++;
                    
                }
                alreadyPresentFrequency.add(currentFreq); // finally add frequecny so that other chanracter with same frequency cannot be added
        }
        
        
        return deletedCharacterCount;
        
    }
}
