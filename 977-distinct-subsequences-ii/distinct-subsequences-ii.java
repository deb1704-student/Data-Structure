class Solution {
    public int distinctSubseqII(String s) {
        int MOD= 1_000_000_007;

        int total=0;
        int[] end= new int[26];

        for(char ch : s.toCharArray()){
            int index = ch - 'a';

            int oldTotal=total;

            int newSubSequences= (oldTotal + 1 - end[index] + MOD) % MOD;

            total =( total + newSubSequences ) % MOD;

            end[index]=( end[index]+ newSubSequences ) % MOD;

        }
        return total;
        
    }
}