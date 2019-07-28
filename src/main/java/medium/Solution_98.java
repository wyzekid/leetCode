package medium;

/**
 * Solution for https://leetcode.com/problems/validate-binary-search-tree/
 */
public class Solution_98 {


    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        if (root.left != null && root.right != null) {
            if (!(root.left.val < root.val && root.right.val > root.val)) {
                return false;
            }
            return isValidBST(root.left) && isValidBST(root.right);
        } else if (root.left == null) {
            if (root.right != null) {
                if (root.right.val <= root.val) {
                    return false;
                } else {
                    return isValidBST(root.right);
                }
            }
            return isValidBST(root.right);
        } else {
            if (root.left.val >= root.val) {
                return false;
            } else {
                return isValidBST(root.left);
            }
        }

    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}