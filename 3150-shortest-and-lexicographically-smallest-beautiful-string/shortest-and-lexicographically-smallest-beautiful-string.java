class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int l=0, count =0;
        String ans="";
        for (int r=0;r<s.length();r++){
            if(s.charAt(r)=='1'){
                count++;
            }
            while(count==k){
                String curr = s.substring(l,r+1);

                if(ans.isEmpty()||ans.length()>curr.length()|| (ans.length()==curr.length() && curr.compareTo(ans)<0) ){
                    ans=curr;
                }

                if(s.charAt(l)=='1'){
                    count--;
                }
                l++;
            }
        }
        return ans;
        
    }
}