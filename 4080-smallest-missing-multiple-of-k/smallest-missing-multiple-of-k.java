class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set= new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
        int currentMultiple=k;
        while(set.contains(currentMultiple)){
            currentMultiple+=k;
        }
        return currentMultiple;
    }
}