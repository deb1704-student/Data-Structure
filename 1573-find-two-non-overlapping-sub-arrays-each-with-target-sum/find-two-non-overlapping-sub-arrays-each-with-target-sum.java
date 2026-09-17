class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // bestTill[i] stores the minimum length of a valid subarray in arr[0...i]
        int[] bestTill = new int[n];
        Arrays.fill(bestTill, Integer.MAX_VALUE);
        
        int left = 0;
        int currentSum = 0;
        int minTotalLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink the window if the sum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }
            
            // If we found a valid subarray
            if (currentSum == target) {
                int currentLength = right - left + 1;
                
                // Check if there is a valid non-overlapping subarray to the left
                if (left > 0 && bestTill[left - 1] != Integer.MAX_VALUE) {
                    minTotalLength = Math.min(minTotalLength, currentLength + bestTill[left - 1]);
                }
                
                // Update the DP array for the current 'right' boundary
                if (right > 0) {
                    bestTill[right] = Math.min(bestTill[right - 1], currentLength);
                } else {
                    bestTill[right] = currentLength;
                }
            } else {
                // If no valid subarray ends at 'right', carry forward the best length seen so far
                if (right > 0) {
                    bestTill[right] = bestTill[right - 1];
                }
            }
        }
        
        // Return the minimum total length if found, otherwise return -1
        return minTotalLength == Integer.MAX_VALUE ? -1 : minTotalLength;
    }
}