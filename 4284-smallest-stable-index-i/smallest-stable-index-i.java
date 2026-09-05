class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Arrays to store prefix maximums and suffix minimums
        int[] prefMax = new int[n];
        int[] suffMin = new int[n];
        
        // Populate prefix maximums
        prefMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefMax[i] = Math.max(prefMax[i - 1], nums[i]);
        }
        
        // Populate suffix minimums
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }
        
        // Check for the smallest stable index
        for (int i = 0; i < n; i++) {
            // Using long to avoid any potential overflow issues during subtraction
            long instabilityScore = (long) prefMax[i] - suffMin[i];
            if (instabilityScore <= k) {
                return i;
            }
        }
        
        return -1;
    }
}