class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        
        // Count the frequency of each character in s
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        char[] result = new char[n];
        
        // Start backtracking from index 0
        // 'isBound = true' means we are currently matching target's prefix exactly
        if (findPermutation(0, true, counts, target, result)) {
            return new String(result);
        }
        
        return "";
    }

     private boolean findPermutation(int index, boolean isBound, int[] counts, String target, char[] result) {
        // Base case: If we successfully filled the array
        if (index == target.length()) {
            // If it's still bounded, it means we matched target exactly (not strictly greater)
            return !isBound;
        }
        
        // If we are bound, we must start searching from target's character.
        // If we are not bound, we can start from 'a' to get the smallest possible permutation.
        int startChar = isBound ? (target.charAt(index) - 'a') : 0;
        
        for (int i = startChar; i < 26; i++) {
            if (counts[i] > 0) {
                counts[i]--; // Use character
                result[index] = (char) ('a' + i);
                
                // We remain tightly bound to target ONLY if we were already bound 
                // AND we picked the exact matching character from target.
                boolean nextBound = isBound && (i == startChar);
                
                if (findPermutation(index + 1, nextBound, counts, target, result)) {
                    return true;
                }
                
                counts[i]++; // Backtrack
            }
        }
        
        return false;
    }
}