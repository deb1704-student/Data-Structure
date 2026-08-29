class Solution {
    static class Pair{
        int value;
        int index;

        Pair(int value,int index){
            this.value=value;
            this.index=index;
        }
    }
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        ArrayList<Pair> arr=new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            arr.add(new Pair(nums[i],i));
        }

        arr.sort((a,b) -> Integer.compare(a.value,b.value));
        int start=0;
        while(start<arr.size()){
            int end=start;

            while(end+1 < arr.size() && arr.get(end + 1).value -arr.get(end).value <= limit){
                end++;
            }
            ArrayList<Integer> indices= new ArrayList<>();

            for(int i=start;i<=end;i++){
                indices.add(arr.get(i).index);
            }

            Collections.sort(indices);

            for(int i=0;i<indices.size();i++){
                nums[indices.get(i)]=arr.get(start+i).value;
            }

            start=end+1;
        }
        return nums;
    }
}