import java.util.*;

class Solution {
    // Custom structure to store the best score and the lexicographically optimal indices path
    static class State {
        long score;
        List<Integer> path;

        State(long score, List<Integer> path) {
            this.score = score;
            this.path = path;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // left
            arr[i][1] = intervals.get(i).get(1); // right
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        // Sort by left boundary, tie-break by original index for stable traversal
        Arrays.sort(arr, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[3], b[3]));

        // Precompute next non-overlapping interval using Binary Search
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr[mid][0] > arr[i][1]) high = mid;
                else low = mid + 1;
            }
            next[i] = low;
        }

        // dp[i][j] stores the best State starting from index i with j picks remaining
        State[][] dp = new State[n + 1][5];
        for (int j = 0; j <= 4; j++) {
            dp[n][j] = new State(0, new ArrayList<>());
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, new ArrayList<>());
        }

        // Bottom-up Iterative DP
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= 4; j++) {
                // Option 1: Skip the current interval
                State skip = dp[i + 1][j];

                // Option 2: Take the current interval
                State nextState = dp[next[i]][j - 1];
                long takeScore = arr[i][2] + nextState.score;
                
                List<Integer> takePath = new ArrayList<>();
                takePath.add(arr[i][3]);
                takePath.addAll(nextState.path);
                Collections.sort(takePath); // Required to ensure paths compare correctly
                
                State take = new State(takeScore, takePath);

                // Tie-breaking check
                if (take.score > skip.score) {
                    dp[i][j] = take;
                } else if (skip.score > take.score) {
                    dp[i][j] = skip;
                } else {
                    // Equal scores: pick the lexicographically smaller path
                    dp[i][j] = comparePaths(take.path, skip.path) < 0 ? take : skip;
                }
            }
        }

        // Output formatting
        List<Integer> bestPath = dp[0][4].path;
        int[] result = new int[bestPath.size()];
        for (int i = 0; i < bestPath.size(); i++) {
            result[i] = bestPath.get(i);
        }
        return result;
    }

    // Standard lexicographical sequence comparison helper method
    private int comparePaths(List<Integer> p1, List<Integer> p2) {
        int len = Math.min(p1.size(), p2.size());
        for (int i = 0; i < len; i++) {
            int cmp = Integer.compare(p1.get(i), p2.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(p1.size(), p2.size());
    }
}
