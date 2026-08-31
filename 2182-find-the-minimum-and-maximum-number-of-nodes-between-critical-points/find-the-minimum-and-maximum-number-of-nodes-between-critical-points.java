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
        ArrayList<Integer> criticalIndex=new ArrayList<>();

        int index=1;
        ListNode prev=head;
        ListNode curr=head.next;
        while(curr.next!=null){
            ListNode next=curr.next;
            boolean isMax= curr.val>prev.val && curr.val>next.val;
            boolean isMin= curr.val<prev.val && curr.val<next.val;

            if(isMax||isMin){
                criticalIndex.add(index);
            }
            prev=curr;
            curr=next;
            index++;
        }

        if(criticalIndex.size()<2){
            return new int[]{-1,-1};
        }

        int minD=Integer.MAX_VALUE;

        for(int i=1;i<criticalIndex.size();i++){
            int d= criticalIndex.get(i)-criticalIndex.get(i-1);
            minD=Math.min(minD,d);
        }

        int maxD= criticalIndex.get(criticalIndex.size()-1)-criticalIndex.get(0);

        return new int[]{minD,maxD};

    }
}