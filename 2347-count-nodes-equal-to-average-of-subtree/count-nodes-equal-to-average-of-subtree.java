/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int matchingNode=0;
    public int averageOfSubtree(TreeNode root) {
        matchingNode=0;
        dfs(root);
        return matchingNode;
    }
    private int[] dfs(TreeNode node){
        if(node==null){
            return new int[]{0,0};
        }

        int[] leftData = dfs(node.left);
        int[] rightData = dfs(node.right);

        int currentSum = leftData[0] + rightData[0] + node.val;
        int currentCount = leftData[1] + rightData[1] + 1;

        if (currentSum / currentCount == node.val){
            matchingNode++;
        }

        return new int[]{currentSum, currentCount};
    }
}