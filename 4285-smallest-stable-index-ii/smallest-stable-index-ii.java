class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Array to store the minimum value from index i to n-1
        int[] suffMin = new int[n];
        
        // Populate the suffix minimum array from right to left
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }
        
        int currentMax = nums[0];
        
        // Iterate from left to right to check each index
        for (int i = 0; i < n; i++) {
            // Update the prefix maximum up to the current index i
            currentMax = Math.max(currentMax, nums[i]);
            
            // Use long to safely prevent integer overflow during subtraction
            long instabilityScore = (long) currentMax - suffMin[i];
            
            if (instabilityScore <= k) {
                return i;
            }
        }
        
        return -1;
    }
}