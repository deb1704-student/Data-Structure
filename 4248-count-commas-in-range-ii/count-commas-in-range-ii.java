class Solution {
    public long countCommas(long n) {
        long totalComma=0;
        long seperator=1000;

        while(seperator<=n){
            totalComma += (n-seperator+1);

            if(seperator > Long.MAX_VALUE/1000){
                break;
            }
            seperator*=1000; 
        }

        return totalComma;
    }
}