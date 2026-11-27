import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }

        // 1. Count factor frequencies
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // 2. Precompute total gain (1 purchased + free copies) for each factor
        int[] valByFactor = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            int val = 0;
            for (int m = f; m <= maxFactor; m += f) {
                val += freq[m];
            }
            valByFactor[f] = val;
        }

        // 3. Filter each individual item (keeps distinct items with the same factor)
        List<int[]> candidates = new ArrayList<>();
        for (int[] item : items) {
            int f = item[0], p = item[1];
            int val = valByFactor[f];
            if (p <= budget && val > p / minPrice) {
                candidates.add(new int[]{p, val});
            }
        }

        candidates.sort(Comparator.comparingInt(a -> a[0]));

        // 4. 0/1 Knapsack over valid candidate items
        int[] dp = new int[budget + 1];
        int currentMaxBudget = 0;

        for (int[] cand : candidates) {
            int p = cand[0], v = cand[1];
            currentMaxBudget = Math.min(budget, currentMaxBudget + p);
            for (int c = currentMaxBudget; c >= p; c--) {
                dp[c] = Math.max(dp[c], dp[c - p] + v);
            }
        }

        // 5. Spend remaining budget on the cheapest item
        int ans = budget / minPrice;
        for (int c = 1; c <= currentMaxBudget; c++) {
            ans = Math.max(ans, dp[c] + (budget - c) / minPrice);
        }

        return ans;
    }
}
