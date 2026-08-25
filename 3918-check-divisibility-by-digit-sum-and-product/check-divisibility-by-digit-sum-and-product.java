class Solution {
    public boolean checkDivisibility(int n) {
        int temp=n;
        int sum=0,product=1;
        while(temp>0){
            int digit=temp%10;
            sum+=digit;
            product*=digit;
            temp/=10;
        }
        int combined=sum+product;

        if(combined!=0){
            return n%combined==0;
        }
        return false;
    }
}