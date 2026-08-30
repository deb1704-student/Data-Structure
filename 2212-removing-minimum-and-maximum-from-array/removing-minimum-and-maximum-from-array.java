class Solution {
    public int minimumDeletions(int[] nums) {
        int n= nums.length;
        int max=nums[0];
        int min=nums[0];
        int maxi=0;
        int mini=0;
        for(int i=1;i<n;i++){
            if(nums[i]>max){
                max=nums[i];
                maxi=i;
            }
            if(nums[i]<min){
                min=nums[i];
                mini=i;
            }
        }
        int left=Math.max(maxi,mini)+1;
        int right=n-Math.min(maxi,mini);
        int both=Math.min(maxi,mini)+1+n-Math.max(maxi,mini);
        return Math.min(left,Math.min(right,both));
    }
}