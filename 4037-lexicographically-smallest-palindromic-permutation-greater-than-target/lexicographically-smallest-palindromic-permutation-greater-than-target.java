class Solution {
    private String result="";
    public String lexPalindromicPermutation(String s, String target) {
        int n=s.length();
        int[] count=new int[26];
        for(char c : s.toCharArray()){
            count[c-'a']++;
        }

        int oddCount=0;
        char midChar=0;
        for(char ch='a';ch<='z';ch++){
            if(count[ch-'a']%2!=0){
                oddCount++;
                midChar=ch;
            }
        }

        if(oddCount>1){
            return "";
        }

        int[] halfCount=new int[26];
        for(int i=0;i<26;i++){
            halfCount[i]=count[i]/2;
        }

        int halfLength=n/2;
        StringBuilder curr=new StringBuilder();
        this.result="";

        solve(curr,halfCount,target,0,false,halfLength,midChar,n);
        return result;
        
    }

    private boolean solve(StringBuilder curr,int[] count,String target,int i,boolean greater,int halfLength,char midChar,int totalLen){
        if(i==halfLength){
            String candidate = buildPalindrome(curr,midChar,totalLen);
            if(candidate.compareTo(target)>0){
                result=candidate;
                return true;
            }
            return false;
        }
        if(greater){
            StringBuilder suffix=new StringBuilder();
            for(char ch='a';ch<='z';ch++){
                int available=count[ch-'a'];
                for(int k=0;k<available;k++){
                    suffix.append(ch);
                }
            }
            curr.append(suffix);
            String candidate = buildPalindrome(curr,midChar,totalLen);
            if(candidate.compareTo(target)>0){
                result=candidate;
                curr.setLength(curr.length()-suffix.length());
                return true;
            }
            curr.setLength(curr.length()-suffix.length());
            return false;
        }

        for(char ch='a';ch<='z';ch++){
            if(count[ch-'a']==0){
                continue;
            }
            if (!greater && ch < target.charAt(i)) {
                continue;
            }
            curr.append(ch);
            count[ch - 'a']--;

            boolean isGreater = greater || (ch > target.charAt(i));
            if (solve(curr, count, target, i + 1, isGreater, halfLength, midChar, totalLen)) {
                return true;
            }
            curr.deleteCharAt(curr.length() - 1);
            count[ch - 'a']++;
        }
        return false;
    }

    private String buildPalindrome(StringBuilder firstHalf,char midChar,int totalLen){
        StringBuilder sb=new StringBuilder(firstHalf);
        if(totalLen%2!=0){
            sb.append(midChar);
        }
        for(int j=firstHalf.length()-1;j>=0;j--){
            sb.append(firstHalf.charAt(j));
        }
        return sb.toString();
    }
}