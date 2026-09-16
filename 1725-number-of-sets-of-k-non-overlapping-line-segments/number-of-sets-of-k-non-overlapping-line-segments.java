class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int totalN = n + k - 1;
        int totalK = 2 * k;
        
        // If it's impossible to pick 2k points out of totalN positions
        if (totalK > totalN) {
            return 0;
        }
        
        // DP array to calculate combinations C(totalN, totalK) using Pascal's Triangle
        int[] dp = new int[totalK + 1];
        dp[0] = 1; // C(0, 0) = 1
        
        for (int i = 1; i <= totalN; i++) {
            // Iterate backwards to compute the current row using values from the previous row
            for (int j = Math.min(i, totalK); j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }
        
        return dp[totalK];
    }
}