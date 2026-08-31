/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int firstCI=-1;
        int prevCI=-1;


        int minD=Integer.MAX_VALUE;

        int index=1;
        ListNode prev=head;
        ListNode curr=head.next;
        while(curr.next!=null){
            ListNode next=curr.next;
            boolean isMax= curr.val>prev.val && curr.val>next.val;
            boolean isMin= curr.val<prev.val && curr.val<next.val;

            if(isMax||isMin){
                if(firstCI==-1){
                    firstCI=index;
                }
                else{
                    int d=index-prevCI;
                    minD=Math.min(minD,d);
                }
                prevCI=index;
            }
            prev=curr;
            curr=next;
            index++;
        }

        if(firstCI==prevCI){
            return new int[]{-1,-1};
        }

        int maxD= prevCI-firstCI;

        return new int[]{minD,maxD};

    }
}