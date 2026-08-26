class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double sum=0;
        for(int i =0;i<k;i++){
            sum+=nums[i];
        }
        double maxS=sum;
        for(int i=k; i<nums.length;i++){
            sum+=nums[i]-nums[i-k];
            maxS=Math.max(maxS,sum);
        }
        return maxS/k;
    }
}