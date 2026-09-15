class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        // dp[i] stores the max palindromes in the prefix of length i
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            // Default case: carry forward the best result from the previous position
            dp[i] = dp[i - 1];
            
            // Check for a palindrome of length k ending at index i - 1
            if (i >= k && isPalindrome(s, i - k, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }
            
            // Check for a palindrome of length k + 1 ending at index i - 1
            if (i >= k + 1 && isPalindrome(s, i - k - 1, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }
        
        return dp[n];
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}